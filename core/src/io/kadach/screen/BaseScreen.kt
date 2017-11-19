package io.kadach.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import io.kadach.FlapFlap
import io.kadach.constant.GameConstants.HEIGHT
import io.kadach.constant.GameConstants.WIDTH
import java.util.*


abstract class BaseScreen(
        protected val game: FlapFlap,
        protected val camera: OrthographicCamera = OrthographicCamera()
) : ScreenAdapter() {

    private val backgroundTexturePaths
            = Array<String>(arrayOf("background-day.png", "background-night.png"))
    private val backgroundTexture
            = Texture(backgroundTexturePaths[Random().nextInt(backgroundTexturePaths.size)])
    private val groundTexture = Texture("base.png")

    private var firstGroundPosition: Vector2
    private var secondGroundPosition: Vector2

    companion object {
        const val GROUND_HEIGHT = 150f
    }

    init {
        camera.setToOrtho(false, WIDTH, HEIGHT)
        firstGroundPosition = Vector2(
                camera.position.x - camera.viewportWidth / 2,
                0f
        )
        secondGroundPosition = Vector2(
                (camera.position.x - camera.viewportWidth / 2) + camera.viewportWidth,
                0f
        )
    }

    open protected fun update(delta: Float) {}

    open protected fun render() {}

    open protected fun handleInput() {}

    open protected fun screenDispose() {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        updateGround()
        update(delta)
        camera.update()
        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        game.batch.draw(
                backgroundTexture,
                camera.position.x - (camera.viewportWidth / 2),
                0f,
                camera.viewportWidth,
                camera.viewportHeight
        )
        render()
        game.batch.draw(
                groundTexture,
                firstGroundPosition.x,
                firstGroundPosition.y,
                camera.viewportWidth,
                GROUND_HEIGHT
        )
        game.batch.draw(
                groundTexture,
                secondGroundPosition.x,
                secondGroundPosition.y,
                camera.viewportWidth,
                GROUND_HEIGHT
        )
        game.batch.end()
        handleInput()
    }

    override fun dispose() {
        backgroundTexture.dispose()
        screenDispose()
    }

    private fun updateGround() {
        if (camera.position.x - camera.viewportWidth / 2 > firstGroundPosition.x + camera.viewportWidth)
            firstGroundPosition.add(camera.viewportWidth * 2, 0f)
        if (camera.position.x - camera.viewportWidth / 2 > secondGroundPosition.x + camera.viewportWidth)
            secondGroundPosition.add(camera.viewportWidth * 2, 0f)
    }

}