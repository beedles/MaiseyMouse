package com.pandapants.game.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pandapants.game.MaiseyMouse;
import com.pandapants.game.entities.MouseFireworks;
import com.pandapants.game.entities.RandomSound;

public class BaseScreen implements Screen {
	MouseFireworks fireworks;
	final MaiseyMouse game;
	
	OrthographicCamera camera;
	
	RandomSound test_sound;
	
	
	protected BaseScreen(final MaiseyMouse _game) {
		game = _game;
		fireworks = new MouseFireworks(game);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.Screen_Width, game.Screen_Height);
		
		test_sound = new RandomSound();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		fireworks.move_fireworks(Gdx.input.getX(), Gdx.input.getY());
		fireworks.render(delta);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Gdx.app.log("Audio", "Sending SOund");
			Random rand = new Random();
			int f_lo = 200;
			int f_hi = 2000;
			int freq = rand.nextInt(f_hi - f_lo) + f_lo;
			test_sound.play_sound(0.5f, freq);
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		test_sound.dispose();
	}

}
