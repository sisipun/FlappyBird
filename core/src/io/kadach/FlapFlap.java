package io.kadach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class FlapFlap extends ApplicationAdapter {

    private Vector3 touchPosition;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture downFlapBird;
	private Texture midFlapBird;
	private Texture upFlapBird;
	private Rectangle bucket;

	@Override
	public void create () {
        touchPosition = new Vector3();

	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		downFlapBird = new Texture("bluebird-downflap.png");
		midFlapBird = new Texture("bluebird-midflap.png");
		upFlapBird = new Texture("bluebird-upflap.png");

		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(downFlapBird, bucket.x, bucket.y);
		batch.end();

		if (Gdx.input.isTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            bucket.x = (int) (touchPosition.x - 64/2);
            bucket.y = (int) (touchPosition.y - 64/2);
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		downFlapBird.dispose();
	}
}
