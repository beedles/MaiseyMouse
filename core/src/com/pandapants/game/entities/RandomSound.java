package com.pandapants.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.math.MathUtils;

public class RandomSound {
	private static int SAMPLE_RATE = 44100;
	
	AudioDevice device = Gdx.audio.newAudioDevice(SAMPLE_RATE, true);
	FloatArray PCM = new FloatArray();
	
	
	boolean playing = false;
	
	//Notes
	static ArrayMap<Integer, String> Notes;
	
	public RandomSound() {
		device.setVolume(1);	
		Notes = new ArrayMap<Integer, String>(7);
		Notes.put(-9, "C");
		Notes.put(-7, "D");
		Notes.put(-5, "E");
		Notes.put(-4, "F");
		Notes.put(-2, "G");
		Notes.put(0, "A");
		Notes.put(2, "B");
	}
	
	public void play_sound(final float duration, final double frequency) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(playing) {
					return;
				}
				try {
					float[] float_array;
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
	
	public boolean play_note(String note, boolean sharp, int octave, float duration) {
		double fixed_note_frequency = 440; //Fixed note of A4 is 440Hz
		double b = (double) 1/12;
		double a = Math.pow(2, b);
		//Half steps is the number of half steps our note/octave is from A4
		int half_steps = 0; 
		double frequency = 0;
		
		if(octave < 0 || octave > 8 || !contains(note) || 
				(octave == 0 && note != "B" && note != "A") ||
				(sharp && (note == "E" || note == "B"))) {
			return false;
		}
		
		half_steps += Notes.getKey(note, false);
		half_steps += (octave - 4) * 12;
		half_steps += sharp ? 1 : 0;
		
		frequency = fixed_note_frequency * Math.pow(a, half_steps);
		
		Gdx.app.log("Audio", "Note " + note + (sharp?"#": "") + String.valueOf(octave));
		Gdx.app.log("Audio", "Key is " + String.valueOf(Notes.getKey(note, false)));
		Gdx.app.log("Audio", "Half Step = " + String.valueOf(half_steps));
		Gdx.app.log("Audio", "a = " + String.valueOf(a));		
		Gdx.app.log("Audio", "Frequency is " + String.valueOf(frequency));

		play_sound(duration, frequency);
		return true;
	}
	
	public void play_twinkle_twinkle() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				play_note("C", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("C", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("G", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("G", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("A", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("A", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("G", false, 4, 2);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				
				play_note("F", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("F", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("E", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("E", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("D", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("D", false, 4, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				play_note("C", false, 4, 2);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		}).start();		
	}
	
	private static boolean contains(String test) {
		return Notes.containsValue(test, false);
	}
	
	public void dispose() {
		device.dispose();
	}
}
