package io.kadach.sprite

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import io.kadach.component.constant.GameConstants.HEIGHT


class Pipe(
        x: Float,
        private val minY: Float,
        private val holeHeight: Float
) {

    private val width = 75f
    private val height = HEIGHT

    val bottomBound: Rectangle get() = Rectangle(bottomPosition.x, bottomPosition.y, width, height)
    val topBound: Rectangle get() = Rectangle(topPosition.x, topPosition.y, width, height)
    val bottomTexture: Texture = Texture("pipe-bottom-green.png")
    val topTexture: Texture = Texture("pipe-top-green.png")

    private val bottomPosition: Vector2 = Vector2(x, getStartY())
    private val topPosition: Vector2 get() = Vector2(bottomPosition).add(0f, height + holeHeight)


    fun collides(player: Rectangle): Boolean = player.overlaps(bottomBound) || player.overlaps(topBound)

    fun dispose() {
        bottomTexture.dispose()
        topTexture.dispose()
    }

    private fun getStartY(): Float{
        val minHeight = -1 * (holeHeight + 50f)
        val maxHeight = -1 * (height - minY) + 50f
        return MathUtils.random(minHeight, maxHeight)
    }

}