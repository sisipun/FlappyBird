package io.kadach.component

import com.badlogic.gdx.utils.Array
import java.util.*


object BackgroundHandler {

    private val backgroundPaths = Array<String>(arrayOf("background-day.png", "background-night.png"))

    fun getBackground(): String = backgroundPaths[Random().nextInt(2)]

}