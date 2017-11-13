package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import io.kadach.FlapFlap
import io.kadach.sprite.Bird
import io.kadach.sprite.Pipe


class GameScreen(
        game: FlapFlap,
        private val bird: Bird = Bird(200f, 400f, GROUND_HEIGHT, BIRD_SPEED, GRAVITY),
        private val pipes: Array<Pipe> = Array()
) : BaseScreen(game) {

    companion object {
        const val PIPE_SPACING = 100f
        const val HOLE_HEIGHT = 150f
        const val GRAVITY = -25f
        const val BIRD_SPEED = 300f
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

        if (pipes.size == 0 || camera.position.x + (camera.viewportWidth / 2) - pipes.last().bottomBound.x > PIPE_SPACING) {
            pipes.add(Pipe(camera.position.x + camera.viewportWidth, GROUND_HEIGHT, HOLE_HEIGHT))
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }

        var death = false
        pipes.forEach {
            if (it.collides(bird.bound)) {
                game.screen = StartScreen(game)
                death = true
            }
        }

        if (bird.bound.y > camera.position.y + (camera.viewportHeight / 2) || bird.bound.y <= GROUND_HEIGHT) {
            game.screen = StartScreen(game)
            death = true
        }

        if (death) dispose()
    }

    override fun screenDispose() {
        bird.dispose()
        pipes.forEach { it.dispose() }
    }

}