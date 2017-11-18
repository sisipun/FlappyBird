package io.kadach.sprite

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import java.util.*


class Bird(
        x: Float,
        y: Float,
        private val horizontalVelocity: Float,
        private val verticalVelocity: Float,
        private val width: Float,
        private val height: Float,
        birdTextures: Array<Texture>,
        private val flySound: Sound? = null
) {

    private val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2 = Vector2(0f, 0f)

    val bound: Rectangle get() = Rectangle(position.x, position.y, width, height)
    val texture: Texture = birdTextures[Random().nextInt(birdTextures.size)]


    fun update(delta: Float) {
        velocity.add(Vector2(0f, verticalVelocity))
        velocity.scl(delta)

        position.add(horizontalVelocity * delta, velocity.y)
        velocity.scl(1 / delta)
    }

    fun jump() {
        velocity.y = 550f
        flySound?.play()
    }

    fun dispose() {
        texture.dispose()
        flySound?.dispose()
    }

}