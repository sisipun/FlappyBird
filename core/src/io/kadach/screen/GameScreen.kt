package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils
import io.kadach.FlapFlap
import io.kadach.model.Bird
import io.kadach.model.Pipe


class GameScreen(
        game: FlapFlap,
        private val bird: Bird = Bird(100f, 400f, GROUND_HEIGHT, GRAVITY),
        private val pipes: Array<Pipe> = Array(),
        private var lastPipeTime: Long = TimeUtils.millis()
) : BaseScreen(game) {

    companion object {
        const val HOLE_HEIGHT = 150f
        const val GRAVITY = -25f
        const val BIRD_SPEED = 5f
    }

    override fun update(delta: Float) {
        bird.update(delta)
        val iterator = pipes.iterator()
        while (iterator.hasNext()) {
            val pipe = iterator.next()
            pipe.update(delta)
            if (pipe.bottomBound.x + pipe.bottomBound.width < 0) {
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

        if (TimeUtils.millis() - lastPipeTime > 1100) {
            pipes.add(Pipe(-1 * BIRD_SPEED, GROUND_HEIGHT, HOLE_HEIGHT))
            lastPipeTime = TimeUtils.millis()
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }

        var collides = false
        pipes.forEach {
            if (it.collides(bird.bound)) {
                game.screen = StartScreen(game)
                collides = true
            }
        }

        if (collides) dispose()
    }

    override fun screenDispose() {
        bird.dispose()
        pipes.forEach { it.dispose() }
    }

}