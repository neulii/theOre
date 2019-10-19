package com.neulii.theOre.graphics;

public class Sprite {
	
	private final int SIZE;
	private int x;
	private int y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.sheet = sheet;
		this.x = x * size;
		this.y = y * size;
		
		load();
	}
	
	public void load() {
		
		for(int y = 0; y<SIZE; y++) {
			for(int x = 0; x<SIZE; x++) {
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x)+ (y + this.y)*sheet.SIZE];
				
			}
		}
		
		
	}

}
