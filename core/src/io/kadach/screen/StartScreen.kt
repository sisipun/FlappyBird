package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import io.kadach.FlapFlap
import io.kadach.FlapFlap.Companion.HEIGHT
import io.kadach.FlapFlap.Companion.WIDTH


class StartScreen(
        game: FlapFlap,
        private val messageTexture: Texture = Texture("message.png"),
        private val message: Rectangle = Rectangle()
) : BaseScreen(game) {

    init {
        message.width = WIDTH
        message.height = HEIGHT
        message.x = WIDTH / 2 - message.width / 2
        message.y = HEIGHT / 2 - message.height / 2
    }

    override fun update(delta: Float) {
    }

    override fun render() {
        game.batch.draw(messageTexture, message.x, message.y, message.width, message.height)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            game.screen = GameScreen(game)
            dispose()
        }
    }

    override fun screenDispose() {
        messageTexture.dispose()
    }

}