package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import io.kadach.FlapFlap


class GameOverScreen(
        game: FlapFlap,
        private val gameOverMessageTexture: Texture = Texture("gameover.png"),
        private val gameOverMessage: Rectangle = Rectangle()
) : BaseScreen(game) {

    init {
        gameOverMessage.width = camera.viewportWidth / 2
        gameOverMessage.height = camera.viewportHeight/ 6
        gameOverMessage.x = camera.viewportWidth / 2 - gameOverMessage.width / 2
        gameOverMessage.y = camera.viewportHeight / 2 - gameOverMessage.height / 2
    }

    override fun update(delta: Float) {
    }

    override fun render() {
        game.batch.draw(gameOverMessageTexture, gameOverMessage.x, gameOverMessage.y, gameOverMessage.width, gameOverMessage.height)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            game.screen = GameScreen(game)
            dispose()
        }
    }

    override fun screenDispose() {
        gameOverMessageTexture.dispose()
    }

}