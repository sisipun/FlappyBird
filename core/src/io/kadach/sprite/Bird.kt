package io.kadach.sprite

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import java.util.*


class Bird(
        x: Float,
        y: Float,
        private val minY: Float,
        private val horizontalVelocity: Float,
        private val verticalVelocity: Float
) {

    private val width = 40f
    private val height = 40f
    private val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2 = Vector2(0f, 0f)
    private val flySound: Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.wav"))
    private val birdTexturePaths = Array<String>(arrayOf("redbird-midflap.png", "bluebird-midflap.png", "yellowbird-midflap.png"))

    val bound: Rectangle get() = Rectangle(position.x, position.y, width, height)
    val texture: Texture = Texture(birdTexturePaths[Random().nextInt(birdTexturePaths.size)])


    fun update(delta: Float) {
        velocity.add(Vector2(0f, verticalVelocity))
        velocity.scl(delta)

        position.add(horizontalVelocity * delta, velocity.y)
        velocity.scl(1 / delta)
        if (position.y < minY) {
            position.y = minY
        }
    }

    fun jump() {
        velocity.y = 550f
        flySound.play()
    }

    fun dispose() {
        texture.dispose()
        flySound.dispose()
    }

}