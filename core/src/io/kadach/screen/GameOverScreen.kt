package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import io.kadach.FlapFlap
import io.kadach.utils.ScoreHelper


class GameOverScreen(
        game: FlapFlap,
        private val score: Int,
        private val gameOverMessageTexture: Texture = Texture("gameover.png"),
        private val scoreMessageTexture: Texture = Texture("score.png"),
        private val highScoreMessageTexture: Texture = Texture("score.png"),
        private val gameOverMessage: Rectangle = Rectangle(),
        private val scoreMessage: Rectangle = Rectangle(),
        private val highScoreMessage: Rectangle = Rectangle()
) : BaseScreen(game) {

    companion object {
        const val SCORE_WIDTH = 25f
        const val SCORE_HEIGHT = 40f
    }

    init {
        if (score > highScore) {
            updateHighScore(score)
        }
        gameOverMessage.width = camera.viewportWidth / 2
        gameOverMessage.height = camera.viewportHeight / 6
        gameOverMessage.x = camera.viewportWidth / 2 - gameOverMessage.width / 2
        gameOverMessage.y = camera.viewportHeight / 2 - gameOverMessage.height / 2
        scoreMessage.width = camera.viewportWidth / 6
        scoreMessage.height = camera.viewportHeight / 16
        scoreMessage.x = camera.viewportWidth / 2 - scoreMessage.width
        scoreMessage.y = camera.viewportHeight / 2.2f - scoreMessage.height / 2 - gameOverMessage.height / 2
        highScoreMessage.width = camera.viewportWidth / 6
        highScoreMessage.height = camera.viewportHeight / 16
        highScoreMessage.x = camera.viewportWidth / 2 - highScoreMessage.width
        highScoreMessage.y = camera.viewportHeight / 2.6f - highScoreMessage.height / 2 - gameOverMessage.height / 2
    }

    override fun render() {
        game.batch.draw(
                gameOverMessageTexture,
                gameOverMessage.x,
                gameOverMessage.y,
                gameOverMessage.width,
                gameOverMessage.height
        )
        game.batch.draw(
                scoreMessageTexture,
                scoreMessage.x,
                scoreMessage.y,
                scoreMessage.width,
                scoreMessage.height
        )
        game.batch.draw(
                highScoreMessageTexture,
                highScoreMessage.x,
                highScoreMessage.y,
                highScoreMessage.width,
                highScoreMessage.height
        )
        val scoreTextures = ScoreHelper.getScore(score)
        scoreTextures.forEachIndexed { index, texture ->
            game.batch.draw(
                    texture,
                    scoreMessage.x + scoreMessage.width + 20f + (25f * (index)),
                    scoreMessage.y + 5f,
                    SCORE_WIDTH,
                    SCORE_HEIGHT
            )
        }
        val highScoreTextures = ScoreHelper.getScore(highScore)
        highScoreTextures.forEachIndexed { index, texture ->
            game.batch.draw(
                    texture,
                    highScoreMessage.x + highScoreMessage.width + 20f + (25f * (index)),
                    highScoreMessage.y + 5f,
                    SCORE_WIDTH,
                    SCORE_HEIGHT
            )
        }
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