package com.pandapants.game.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.pandapants.game.MaiseyMouse;
import com.pandapants.game.entities.Animal;

public class WalkingAnimals extends BaseScreen {
	Color screen_colour;
	
	final GlyphLayout layout;
	final String title = "MAISEY'S GAME";
	
	Animal current_animal;
	Array<Animal> animals;
	
	int animal_direction = 0;
	int animal_move_x = 0;
	int animal_move_y = 0;
	float animal_x = 0;
	float animal_y = 0;
	float animal_speed = 60;
	float frame_duration = 0.1f;
	int animal_scale = 4;
	
	public WalkingAnimals(final MaiseyMouse _game) {
		super(_game);
		
		screen_colour = new Color(0.5f, 0.5f, 0.2f, 1);
		layout = new GlyphLayout(game.title_font, title);
		
		//Load Animal texture regions
		Load_Animals();		
	}

	private void Load_Animals() {
		String[] animal_list = new String[] {
			"chicken",
			"cow",
			"llama",
			"pig",
			"sheep"
		};
		
		animals = new Array<Animal>();
		for(int i = 0; i < animal_list.length; i++) {
			animals.add(new Animal(animal_list[i], "walk", frame_duration));
		}
		/*animals.add(new Animal("entities/chicken_walk.atlas", frame_duration));
		animals.add(new Animal("entities/cow_walk.atlas", frame_duration));
		animals.add(new Animal("entities/llama_walk.atlas", frame_duration));
		animals.add(new Animal("entities/pig_walk.atlas", frame_duration));
		animals.add(new Animal("entities/sheep_walk.atlas", frame_duration));*/
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
		game.menu_font.draw(game.batch, "Press a key to make the animals walk", 100, 150);
		game.menu_font.draw(game.batch, "Ctrl + Shift + Q to go back", 100, 100);
		if(current_animal != null) {
			game.batch.draw(current_animal.get_current_frame(), 
					animal_x, animal_y,
					0, 0, 
					current_animal.get_sprite_width(), current_animal.get_sprite_height(), 
					animal_scale, animal_scale, 90, true);
		}
		
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
		
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			if(animals.size > 0) {
				if(current_animal == null) {
					Random rand = new Random();
					
					//Choose a new animal
					current_animal = animals.random();
					
					//Set the direction
					animal_direction = 
							rand.nextInt(current_animal.get_max_directions() - 1);
					current_animal.set_direction(animal_direction);
					
					current_animal.update(delta);
					
					Set_Start_Coordinates();
				} else {
					//Move the animal or change to a new one if it moves off the page
					current_animal.update(delta);	
					animal_y += animal_move_y * animal_speed * animal_scale * delta;
					animal_x += animal_move_x * animal_speed * animal_scale * delta;
					if((animal_direction == 0 && animal_y > game.Screen_Height) ||
							(animal_direction == 1 && animal_x < 0) ||
							(animal_direction == 2 && animal_y < 0) ||
							(animal_direction == 3 && animal_x > game.Screen_Width)) {
						current_animal = null;
					}
				}
			}
		}
	}

	private void Set_Start_Coordinates() {
		Random rand = new Random();
		switch(animal_direction) {
			case 0:
				animal_x = rand.nextInt(game.Screen_Width - 
						(2 * current_animal.get_sprite_width() * animal_scale)) + 
						current_animal.get_sprite_width() * animal_scale;
				animal_y = 0 - (current_animal.get_sprite_height() * animal_scale);
				animal_move_x = 0;
				animal_move_y = 1;
				break;
			case 1:
				animal_x = game.Screen_Width;
				animal_y = rand.nextInt(game.Screen_Height - 
						(2 * current_animal.get_sprite_height() * animal_scale)) + 
						current_animal.get_sprite_height() * animal_scale;
				animal_move_x = -1;
				animal_move_y = 0;
				break;
			case 2:
				animal_x = rand.nextInt(game.Screen_Width - 
						(2 * current_animal.get_sprite_width() * animal_scale)) + 
						current_animal.get_sprite_width() * animal_scale;
				animal_y = game.Screen_Height;
				animal_move_x = 0;
				animal_move_y = -1;
				break;
			default:
				animal_x = 0;
				animal_y = rand.nextInt(game.Screen_Height - 
						(2 * current_animal.get_sprite_height() * animal_scale)) + 
						current_animal.get_sprite_height() * animal_scale;
				animal_move_x = 1;
				animal_move_y = 0;
				break;				
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
