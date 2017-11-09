package io.kadach.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import io.kadach.component.GameConstants.BIRD_SPEED
import io.kadach.component.GameConstants.HEIGHT
import io.kadach.component.GameConstants.HOLE_HEIGHT
import io.kadach.component.GameConstants.WIDTH


class Pipe(y: Float) {

    val bottomPosition: Vector3 = Vector3(WIDTH, y, 0f)
    val topPosition: Vector3 get() = Vector3(bottomPosition).add(0f, height + HOLE_HEIGHT, 0f)
    private val velocity: Vector3 = Vector3(-1 * BIRD_SPEED, 0f, 0f)
    val bottomTexture: Texture = Texture("pipe-bottom-green.png")
    val topTexture: Texture = Texture("pipe-top-green.png")
    val width = 75f
    val height = HEIGHT

    fun update(delta: Float) {
        bottomPosition.add(velocity)
    }

}