package io.kadach.sprite

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import io.kadach.animation.FlyAnimation
import java.util.*


class Bird(
        x: Float,
        y: Float,
        private val horizontalVelocity: Float,
        private val verticalVelocity: Float,
        private val width: Float,
        private val height: Float,
        birdTextures: Array<Texture>,
        frameCount: Int,
        private val flySound: Sound? = null
) {

    private val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2 = Vector2(0f, 0f)
    private val flyAnimation: FlyAnimation
            = FlyAnimation(TextureRegion(birdTextures[Random().nextInt(birdTextures.size)]), frameCount, 0.5f)

    val bound: Rectangle get() = Rectangle(position.x, position.y, width, height)
    val texture: TextureRegion get() = flyAnimation.getFrame()

    fun update(delta: Float) {
        flyAnimation.update(delta)
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
        flySound?.dispose()
    }

}