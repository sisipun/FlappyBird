package io.kadach

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.kadach.screen.StartScreen


class FlapFlap : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
//        val music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"))
//        music.isLooping = true
//        music.play()
        setScreen(StartScreen(this))
    }

}
