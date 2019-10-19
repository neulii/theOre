package com.neulii.theOre.graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.SIZE = size;
		this.path = path;
		pixels = new int[SIZE * SIZE];
		load();
		
	}
	
	public void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			
			image.getRGB(0, 0, w, h, pixels, 0, SIZE);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}

}
