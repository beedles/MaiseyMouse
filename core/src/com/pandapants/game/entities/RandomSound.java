package com.pandapants.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.math.MathUtils;

public class RandomSound {
	private static int SAMPLE_RATE = 44100;
	
	AudioDevice device = Gdx.audio.newAudioDevice(SAMPLE_RATE, true);
	FloatArray PCM = new FloatArray();
	float[] float_array;
	
	boolean playing = false;
	
	public RandomSound() {
		device.setVolume(1);		
	}
	
	public void play_sound(final float duration, final float frequency) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(playing) {
					return;
				}
				try {
					playing = true;
					double d_num_samples = duration * SAMPLE_RATE;
					int num_samples = (int) d_num_samples;
					Gdx.app.log("Audio", "Expected number of samples " + String.valueOf(num_samples));
					float_array = new float[num_samples];
					for(int i = 0; i < num_samples; i++) {
						float_array[i] = (float) Math.sin(frequency * 2 * MathUtils.PI * i / SAMPLE_RATE);
					}
					
					Gdx.app.log("Audio", "Number of samples " + String.valueOf(float_array.length));
					
					device.writeSamples(float_array, 0, float_array.length);
					Gdx.app.log("Audio", "Sent Sound");
				} catch(GdxRuntimeException e) {
					Gdx.app.log("Audio", e.getMessage());
				} finally {
					playing = false;
				}
			}
		}).start();		
	}
	
	public void dispose() {
		device.dispose();
	}
}
