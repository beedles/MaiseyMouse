package com.pandapants.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class Animal {
	private static final int NUM_DIRECTIONS = 4;
	private static final int NUM_ANIMATION_TYPES = 1;
	/*Directions:
	 * 0 Up
	 * 1 Left
	 * 2 Down
	 * 3 Right
	 */
	
	int direction = 0;
	/*Animation Types:
	 * 0 Walk
	 *
	 * 
	 */	
	int animation_type = 0;
	
	ArrayMap<Integer , Array<Animation>> animations;
	Array<Animation> animation_array;
	Animation animation;
	Texture sheet;
	TextureAtlas texture_atlas;
	TextureRegion[] frames;
	TextureRegion current_frame;
		
	float state_time;
	
	
	/*public Animal(int _frame_cols, int _frame_rows, int _frame_width,
			int _frame_height, float duration, String sheet_file) {
		int index = 0;
		TextureRegion[][] tmp;
		
		frame_cols = _frame_cols;
		frame_rows = _frame_rows;
		frame_height = _frame_height;
		frame_width = _frame_width;
		try {
			sheet = new Texture(Gdx.files.internal(sheet_file));
		} catch (GdxRuntimeException e) {
			System.out.print("Failed to load image: " + sheet_file);
			System.out.flush();
			return;
		}			
		
		tmp = TextureRegion.split(sheet, frame_width, frame_height);
		frames = new TextureRegion[frame_cols * frame_rows];
		for(int i = 0; i < frame_rows; i++) {
			for(int j = 0; j < frame_cols; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		
		animation = new Animation(duration, frames);
		
		sprite_batch = new SpriteBatch();
		state_time = 0f;
	}*/
	
	public Animal(String atlas_file, float duration) {
		Load_Animal_File(atlas_file, duration);		
	}
	
	public Animal(String animal_name, String animation_name, float duration) {
		String atlas_file = "entities/" + animal_name + "_" + animation_name + ".atlas";
		Load_Animal_File(atlas_file, duration);	
	}
	
	private void Load_Animal_File(String atlas_file, float duration) {
		animation_array = new Array<Animation>(NUM_DIRECTIONS);
		animations = new ArrayMap<Integer , Array<Animation>>(NUM_ANIMATION_TYPES);
		
		for(int i = 0; i < 4; i++) {
			frames = Get_Animal_Frames(atlas_file, "walk_", i);
			animation = new Animation(duration, frames);
			animation_array.add(animation);		
		}
		animations.put(0, animation_array);
		
		state_time = 0f;
	}


	private TextureRegion[] Get_Animal_Frames(String atlas_file, String label, 
			int dir) {
		texture_atlas = new TextureAtlas(Gdx.files.internal(atlas_file));
		switch(dir) {
			case 0:
				label += "up";
				break;
			case 1:
				label += "left";
				break;
			case 2:
				label += "down";
				break;
			case 3:
				label += "right";
				break;
			default:
				return null;
		}
		TextureRegion[] frames = new TextureRegion[texture_atlas.findRegions(label).size];
		
		frames = texture_atlas.findRegions(label).toArray(TextureRegion.class);

		return frames;
	}


	public void update(float delta_time) {
		state_time += delta_time;
		current_frame = animations
				.get(animation_type)
				.get(direction)
				.getKeyFrame(state_time, true);
	}
	
	public TextureRegion get_current_frame() {		
		return current_frame;
	}
	
	public void set_direction(int _direction) {
		if(_direction < NUM_DIRECTIONS) {
			direction = _direction;
		}
	}
	
	public int get_sprite_width() {
		return current_frame.getRegionWidth();
	}
	
	public int get_sprite_height() {
		return current_frame.getRegionHeight();
	}
	
	public int get_max_directions() {
		return NUM_DIRECTIONS;
	}
}
