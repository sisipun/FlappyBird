package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import io.kadach.FlapFlap
import io.kadach.component.BackgroundHandler
import io.kadach.component.GameConstants.GROUND_HEIGHT
import io.kadach.component.GameConstants.HEIGHT
import io.kadach.component.GameConstants.WIDTH


abstract class BaseScreen(
        protected val game: FlapFlap,
        private val camera: OrthographicCamera = OrthographicCamera(),
        private val backgroundTexture: Texture = Texture(BackgroundHandler.getBackground()),
        private val groundTexture: Texture = Texture("base.png")
) : ScreenAdapter() {

    init {
        camera.setToOrtho(false, WIDTH, HEIGHT)
    }

    abstract fun update(delta: Float)

    abstract fun render()

    abstract fun handleInput()

    abstract fun screenDispose()

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()
        update(delta)
        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        game.batch.draw(backgroundTexture, 0f, 0f, WIDTH, HEIGHT)
        render()
        game.batch.draw(groundTexture, 0f, 0f, WIDTH, GROUND_HEIGHT)
        game.batch.end()
        handleInput()
    }

    override fun dispose() {
        backgroundTexture.dispose()
        screenDispose()
    }
}