package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils
import io.kadach.FlapFlap
import io.kadach.model.Bird
import io.kadach.model.Pipe
import io.kadach.storage.GameConstants.HEIGHT
import io.kadach.storage.GameConstants.HOLE_HEIGHT


class GameScreen(
        game: FlapFlap,
        private val bird: Bird = Bird(100f, 400f ),
        private val pipes: Array<Pipe> = Array(),
        private var lastPipeTime: Long = TimeUtils.millis()
) : BaseScreen(game) {

    override fun update(delta: Float) {
        bird.update(delta)
        val iterator = pipes.iterator()
        while (iterator.hasNext()) {
            val pipe = iterator.next()
            pipe.update(delta)
            if (pipe.bottomPosition.x + pipe.width < 0) {
                iterator.remove()
            }
        }
    }

    override fun render() {
        game.batch.draw(bird.texture, bird.position.x, bird.position.y, bird.width, bird.height)
        pipes.forEach {
            game.batch.draw(it.bottomTexture, it.bottomPosition.x, it.bottomPosition.y, it.width, it.height)
            game.batch.draw(it.topTexture, it.topPosition.x, it.topPosition.y, it.width, it.height)
        }

        if (TimeUtils.millis() - lastPipeTime > 1100) {
            pipes.add(Pipe(MathUtils.random(-1* HOLE_HEIGHT, -1 * HEIGHT)))
            lastPipeTime = TimeUtils.millis()
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) bird.jump()
    }

    override fun screenDispose() {
        bird.texture.dispose()
        pipes.forEach {
            it.bottomTexture.dispose()
            it.topTexture.dispose()
        }
    }

}