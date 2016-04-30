package com.pandapants.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pandapants.game.screens.MainMenuScreen;

public class MaiseyMouse extends Game {
	public SpriteBatch batch;
	public BitmapFont title_font;
	public BitmapFont menu_font;
	
	public final int Screen_Width = 1920;
	public final int Screen_Height = 1080;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		title_font = new BitmapFont(Gdx.files.internal("title_font.fnt"));
		menu_font = new BitmapFont(Gdx.files.internal("menu_font.fnt"));
		
		//Set to menu screen
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		title_font.dispose();
		menu_font.dispose();
	}
}
