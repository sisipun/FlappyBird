package io.kadach

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.kadach.screen.StartScreen


class FlapFlap : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        setScreen(StartScreen(this))
    }

}
