package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import io.kadach.FlapFlap


class StartScreen(
        game: FlapFlap,
        private val startMessageTexture: Texture = Texture("message.png"),
        private val startMessage: Rectangle = Rectangle()
) : BaseScreen(game) {

    init {
        startMessage.width = camera.viewportWidth / 2
        startMessage.height = camera.viewportHeight / 2
        startMessage.x = camera.viewportWidth / 2 - startMessage.width / 2
        startMessage.y = camera.viewportHeight / 2 - startMessage.height / 2
    }

    override fun update(delta: Float) {
    }

    override fun render() {
        game.batch.draw(startMessageTexture, startMessage.x, startMessage.y, startMessage.width, startMessage.height)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            game.screen = GameScreen(game)
            dispose()
        }
    }

    override fun screenDispose() {
        startMessageTexture.dispose()
    }

}