package io.kadach.component.handler

import com.badlogic.gdx.utils.Array
import java.util.*


object BackgroundTextureHandler {

    private val backgroundTexturePaths = Array<String>(arrayOf("background-day.png", "background-night.png"))

    fun getBackgroundTexture(): String = backgroundTexturePaths[Random().nextInt(2)]

}