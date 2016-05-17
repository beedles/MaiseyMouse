package com.pandapants.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.pandapants.game.MaiseyMouse;

public class MouseFireworks {
	ParticleEffect pe;
	MaiseyMouse game;
	
	public MouseFireworks(MaiseyMouse _game) {
		game = _game;
		
		pe = new ParticleEffect();
		pe.load(Gdx.files.internal("particle_effects/basic_flame"), Gdx.files.internal(""));
		pe.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		pe.start();
	}
	
	public void render(float delta) {
		pe.update(delta);
		
		game.batch.begin();
		pe.draw(game.batch);
		game.batch.end();
		if(pe.isComplete()) {
			pe.reset();
		}
	}
	
	public void move_fireworks(float x, float y) {
		pe.setPosition(x, Gdx.graphics.getHeight()-y);
	}
}
