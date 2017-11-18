package io.kadach.sprite

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2


class Pipe(
        x: Float,
        y: Float,
        private val holeHeight: Float,
        private val width: Float,
        private val height: Float
) {

    private val bottomPosition: Vector2 = Vector2(x, y)
    private val topPosition: Vector2 get() = Vector2(bottomPosition).add(0f, height + holeHeight)
    private var active = true

    val bottomBound: Rectangle get() = Rectangle(bottomPosition.x, bottomPosition.y, width, height)
    val topBound: Rectangle get() = Rectangle(topPosition.x, topPosition.y, width, height)
    val holeBound: Rectangle get() = Rectangle(bottomPosition.x + width / 2, bottomPosition.y + height, 0f, holeHeight)
    val bottomTexture: Texture = Texture("pipe-bottom-green.png")
    val topTexture: Texture = Texture("pipe-top-green.png")


    fun isCollidesPipe(player: Rectangle): Boolean = player.overlaps(bottomBound) || player.overlaps(topBound)

    fun isCollidesHole(player: Rectangle): Boolean {
        if (active && player.overlaps(holeBound)) {
            active = false
            return true
        }

        return false
    }

    fun dispose() {
        bottomTexture.dispose()
        topTexture.dispose()
    }

}