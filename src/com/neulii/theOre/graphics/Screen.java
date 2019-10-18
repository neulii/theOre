package com.neulii.theOre.graphics;

import java.util.Random;

public class Screen {

	private int width;
	private int height;

	public int[] pixels;
	
	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	public int[] tiles = new int[64 * 64];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		// random color for each tile
		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	// render pixels
	public void render(int xOffset, int yOffset) {

		for (int y = 0; y < height; y++) {

			int yy = y + yOffset;

			for (int x = 0; x < width; x++) {

				int xx = x + xOffset;

				int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4)  & MAP_SIZE_MASK) * MAP_SIZE;
				

				pixels[x + y * width] = tiles[tileIndex];

			}
		}
	}

	// clear screen
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000; // black
		}

	}

}
