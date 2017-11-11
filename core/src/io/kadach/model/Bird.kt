package io.kadach.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import io.kadach.component.handler.BirdTextureHandler


class Bird(x: Float, y: Float, private val minY: Float, private val fallSpeed: Float) {

    val bound: Rectangle get() = Rectangle(position.x, position.y, width, height)
    val texture: Texture = Texture(BirdTextureHandler.getBirdTexture())

    private val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2 = Vector2(0f, 0f)
    private val width = 40f
    private val height = 40f
    private val flySound: Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))


    fun update(delta: Float) {
        velocity.add(Vector2(0f, fallSpeed))
        velocity.scl(delta)

        position.add(velocity)
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