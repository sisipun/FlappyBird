package io.kadach.screen

import com.badlogic.gdx.Gdx
import io.kadach.FlapFlap
import io.kadach.model.Bird


class GameScreen(
        game: FlapFlap,
        private val bird: Bird = Bird(100f, 400f )
) : BaseScreen(game) {

    override fun update(delta: Float) {
        bird.update(delta)
    }

    override fun render() {
        game.batch.draw(bird.texture, bird.position.x, bird.position.y, bird.width, bird.height)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) bird.jump()
    }

    override fun screenDispose() {
        bird.texture.dispose()
    }

}