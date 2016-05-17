package com.pandapants.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.pandapants.game.MaiseyMouse;

public class MainMenuScreen extends BaseScreen{
	
	
	final GlyphLayout layout;
	final String title = "MAISEY'S GAME";
	
	//MouseFireworks fireworks;
	
	public MainMenuScreen(final MaiseyMouse _game) {
		super(_game);		

		layout = new GlyphLayout(game.title_font, title);
		
		//fireworks = new MouseFireworks(game);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render(delta);
		
//		fireworks.move_fireworks(Gdx.input.getX(), Gdx.input.getY());
//		fireworks.render(delta);
		
		game.batch.begin();
		game.title_font.draw(game.batch, layout, 
				(game.Screen_Width - layout.width)/2, game.Screen_Height - 30);
		game.menu_font.draw(game.batch, "Press 1 to start the Random Colour Game", 100, 180);		
		game.menu_font.draw(game.batch, "Press 2 to start the Animal Game", 100, 150);
		game.menu_font.draw(game.batch, "Press Esc to Exit", 100, 120);
		game.batch.end();
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			//Switch to game screen
			game.setScreen(new RandomColours(game));
			dispose();
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			//Switch to game screen
			game.setScreen(new WalkingAnimals(game));
			dispose();
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
		super.dispose();
		
	}
}
