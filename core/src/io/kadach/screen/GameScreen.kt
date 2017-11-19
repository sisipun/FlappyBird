package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import io.kadach.FlapFlap
import io.kadach.constant.GameConstants.HEIGHT
import io.kadach.sprite.Bird
import io.kadach.sprite.Pipe
import io.kadach.utils.ScoreHelper


class GameScreen(game: FlapFlap) : BaseScreen(game) {

    private val bird: Bird = Bird(
            BIRD_START_POSITION_X,
            BIRD_START_POSITION_Y,
            BIRD_SPEED,
            GRAVITY,
            BIRD_WIDTH,
            BIRD_HEIGHT,
            Array(
                    arrayOf(
                            Texture("redbirdanimation.png"),
                            Texture("bluebirdanimation.png"),
                            Texture("yellowbirdanimation.png"))
            ),
            3,
            Gdx.audio.newSound(Gdx.files.internal("sfx_wing.wav"))
    )
    private val scoreSound: Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_point.wav"))
    private val dieSound: Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_hit.wav"))
    private val pipes: Array<Pipe> = Array()
    private var score = 0

    companion object {
        const val PIPE_SPACING = 100f
        const val HOLE_HEIGHT = 150f
        const val GRAVITY = -25f
        const val BIRD_SPEED = 300f
        const val BIRD_START_POSITION_X = 200f
        const val BIRD_START_POSITION_Y = 400f
        const val BIRD_WIDTH = 40f
        const val BIRD_HEIGHT = 40f
        const val PIPE_WIDTH = 75f
        const val PIPE_HEIGHT = HEIGHT
        const val SCORE_WIDTH = 35f
        const val SCORE_HEIGHT = 50f
    }

    override fun update(delta: Float) {
        bird.update(delta)
        camera.position.x = bird.bound.x + 100
        val iterator = pipes.iterator()
        while (iterator.hasNext()) {
            val pipe = iterator.next()
            if (pipe.bottomBound.x + pipe.bottomBound.width < camera.position.x - (camera.viewportWidth / 2)) {
                iterator.remove()
            }
        }
    }

    override fun render() {
        game.batch.draw(bird.texture, bird.bound.x, bird.bound.y, bird.bound.width, bird.bound.height)
        pipes.forEach {
            game.batch.draw(it.bottomTexture, it.bottomBound.x, it.bottomBound.y, it.bottomBound.width, it.bottomBound.height)
            game.batch.draw(it.topTexture, it.topBound.x, it.topBound.y, it.topBound.width, it.topBound.height)
        }
        val textures = ScoreHelper.getScore(score)
        textures.forEachIndexed { index, texture ->
            game.batch.draw(texture, camera.position.x + (35f * (index - textures.size + 1)), camera.position.y + camera.viewportHeight / 2.7f, SCORE_WIDTH, SCORE_HEIGHT)
        }

        if (pipes.size == 0 || camera.position.x + (camera.viewportWidth / 2) - pipes.last().bottomBound.x > PIPE_SPACING) {
            pipes.add(Pipe(camera.position.x + camera.viewportWidth, generatePipeY(), HOLE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT))
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }

        var death = false
        pipes.forEach {
            if (it.isCollidesHole(bird.bound)) {
                score++
                scoreSound.play()
            }

            if (it.isCollidesPipe(bird.bound)) {
                death()
                death = true
            }
        }

        if (bird.bound.y >= camera.position.y + (camera.viewportHeight / 2) || bird.bound.y <= GROUND_HEIGHT) {
            death()
            death = true
        }

        if (death) dispose()
    }

    override fun screenDispose() {
        bird.dispose()
        pipes.forEach { it.dispose() }
    }

    private fun death() {
        dieSound.play()
        game.screen = GameOverScreen(game)
    }

    private fun generatePipeY(): Float {
        val minHeight = -1 * (HOLE_HEIGHT + 100f)
        val maxHeight = -1 * (PIPE_HEIGHT - GROUND_HEIGHT) + 100f
        return MathUtils.random(minHeight, maxHeight)
    }

}