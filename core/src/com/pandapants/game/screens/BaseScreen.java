package com.pandapants.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pandapants.game.MaiseyMouse;
import com.pandapants.game.entities.MouseFireworks;

public class BaseScreen implements Screen {
	MouseFireworks fireworks;
	final MaiseyMouse game;
	
	OrthographicCamera camera;
	
	
	protected BaseScreen(final MaiseyMouse _game) {
		game = _game;
		fireworks = new MouseFireworks(game);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.Screen_Width, game.Screen_Height);
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
		
	}

}
