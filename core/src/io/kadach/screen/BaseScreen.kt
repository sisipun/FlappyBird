package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import io.kadach.FlapFlap
import io.kadach.storage.GameConstants.HEIGHT
import io.kadach.storage.GameConstants.WIDTH


abstract class BaseScreen(
        val game: FlapFlap,
        private val camera: OrthographicCamera = OrthographicCamera(),
        private val background: Texture = Texture("background-day.png")
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
        game.batch.draw(background, 0f, 0f, WIDTH, HEIGHT)
        render()
        game.batch.end()
        handleInput()
    }

    override fun dispose() {
        background.dispose()
        screenDispose()
    }
}