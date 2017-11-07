package io.kadach.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import io.kadach.component.GameConstants.GRAVITY


class Bird(x: Float, y: Float) {

    val position: Vector3 = Vector3(x, y, 0f)
    private val velocity: Vector3 = Vector3(0f, 0f, 0f)
    val texture: Texture = Texture("bluebird-downflap.png")
    private val flySound: Sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))
    val width = 50f
    val height = 50f

    fun update(delta: Float) {
        velocity.add(Vector3(0f, GRAVITY, 0f))
        velocity.scl(delta)

        position.add(velocity)
        velocity.scl(1 / delta)
        if (position.y < 0) {
            position.y = 0f
        }
    }

    fun jump() {
        velocity.y = 450f
        flySound.play()
    }

}