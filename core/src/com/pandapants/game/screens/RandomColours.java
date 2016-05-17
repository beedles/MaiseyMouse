package com.pandapants.game.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.pandapants.game.MaiseyMouse;

public class RandomColours extends BaseScreen {
	Color screen_colour;
	
	final GlyphLayout layout;
	final String title = "MAISEY'S GAME";
	
	public RandomColours(final MaiseyMouse _game) {
		super(_game);
		
		screen_colour = new Color(0.5f, 0.5f, 0.2f, 1);
		layout = new GlyphLayout(game.title_font, title);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(screen_colour.r, screen_colour.g, screen_colour.b, 
				screen_colour.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render(delta);
		
		game.batch.begin();
		
		game.title_font.draw(game.batch, layout, 
				(game.Screen_Width - layout.width)/2, game.Screen_Height - 30);
		game.menu_font.draw(game.batch, "Press a key to change the colour", 100, 150);
		game.menu_font.draw(game.batch, "Ctrl + Shift + Q to go back", 100, 100);
		game.batch.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || 
				Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || 
					Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
				if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
					//Switch to main menu
					game.setScreen(new MainMenuScreen(game));
					
					dispose();
				}
			}			
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Random rand = new Random();
			screen_colour.r = rand.nextFloat();
			screen_colour.g = rand.nextFloat();
			screen_colour.b = rand.nextFloat();
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
