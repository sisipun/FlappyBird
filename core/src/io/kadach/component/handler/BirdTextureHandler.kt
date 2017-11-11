package io.kadach.component.handler

import com.badlogic.gdx.utils.Array
import java.util.*


object BirdTextureHandler {

    private val birdTexturePaths = Array<String>(arrayOf("redbird-midflap.png", "bluebird-midflap.png", "yellowbird-midflap.png"))

    fun getBirdTexture(): String = birdTexturePaths[Random().nextInt(3)]

}