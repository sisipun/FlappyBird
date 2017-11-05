package io.kadach

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.kadach.screen.StartScreen


class FlapFlap : Game() {

    companion object {
        const val WIDTH = 480F
        const val HEIGHT = 800F
    }

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        setScreen(StartScreen(this))
    }

}
