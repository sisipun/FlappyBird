package io.kadach.animation

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array


class FlyAnimation(
        region: TextureRegion,
        private val frameCount: Int,
        cycleTime: Float
) {

    private var frames: Array<TextureRegion> = Array()
    private var maxFrameTime: Float = cycleTime / frameCount
    private var currentFrameTime: Float = 0f
    private var frame: Int = 0


    init {
        val frameWidth = region.regionWidth / frameCount
        for (i in 0 until frameCount) {
            frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
        }
    }

    fun update(dt: Float) {
        currentFrameTime += dt
        if (currentFrameTime > maxFrameTime) {
            frame++
            currentFrameTime = 0f
        }
        if (frame >= frameCount)
            frame = 0
    }

    fun getFrame(): TextureRegion = frames[frame]

}