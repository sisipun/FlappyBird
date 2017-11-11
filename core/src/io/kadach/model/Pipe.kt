package io.kadach.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import io.kadach.component.constant.GameConstants.HEIGHT
import io.kadach.component.constant.GameConstants.WIDTH


class Pipe(private val horizontalVelocity: Float, private val minY: Float, private val holeHeight: Float) {

    val bottomBound: Rectangle get() = Rectangle(bottomPosition.x, bottomPosition.y, width, height)
    val topBound: Rectangle get() = Rectangle(topPosition.x, topPosition.y, width, height)
    val bottomTexture: Texture = Texture("pipe-bottom-green.png")
    val topTexture: Texture = Texture("pipe-top-green.png")

    private val bottomPosition: Vector2 = Vector2(WIDTH, getStartY())
    private val topPosition: Vector2 get() = Vector2(bottomPosition).add(0f, height + holeHeight)
    private val velocity: Vector2 = Vector2(horizontalVelocity, 0f)
    private val width = 75f
    private val height = HEIGHT


    fun update(delta: Float) {
        bottomPosition.add(velocity)
    }

    fun collides(player: Rectangle): Boolean = player.overlaps(bottomBound) || player.overlaps(topBound)

    fun dispose() {
        bottomTexture.dispose()
        topTexture.dispose()
    }

    private fun getStartY(): Float{
        val minHeight = -1 * (holeHeight + 50f)
        val maxHeight = -1 * (HEIGHT - minY) + 50f
        return MathUtils.random(minHeight, maxHeight)
    }

}