package com.neulii.theOre;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.neulii.theOre.graphics.Screen;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private int width = 300;
    private int height = width / 16 * 9;
    private  int scale = 3;
    
    public static String title = "The Ore";

    private boolean running = false;
    
    private Thread thread;
    private JFrame frame;
    
    private Screen screen;

    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    public Game(){
    	
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        
        frame = new JFrame();

    }
    public synchronized void start(){

    	running = true;

        thread = new Thread(this, "Display");
        thread.start();
        
    }

    public void stop(){

    	running = false;

        try{
            thread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
    	
    	long lastTime = System.nanoTime();
    	long timer = System.currentTimeMillis();
    	final double ns = 1_000_000_000.0 / 60;
    	double delta = 0;
    	
    	int frames = 0;
    	int updates = 0;
    	
        while(running) {	
        	long now = System.nanoTime();
        	
        	delta = delta + (now-lastTime)/ns;
        	lastTime = now;
        	
        	while(delta>=1) {
        		update();
        		updates++;
        		delta--;
        	}
        	
        	render(); //fast as possible
        	frames++;
        
        	if(System.currentTimeMillis() - timer > 1000) {
        		
        		frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");

        		timer = timer + 1000;
        		
        		frames = 0;
        		updates = 0;
        	}
        }
        stop();
    }
    
    public void update() {
    	
    	
    }
    
    public void render() {
    	
    	BufferStrategy bs = getBufferStrategy();
    	
    	if(bs==null) {
    		createBufferStrategy(3);
    		return;
    	}
    	
    	screen.clear();
    	screen.render();
    	
    	for(int i = 0; i<pixels.length; i++) {
    		pixels[i]= screen.pixels[i];
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	
    	//drawing operations begin
    	
    		//set Background black
	    	g.setColor(Color.black);
	    	g.fillRect(0, 0, getWidth(), getHeight());
	    	
	    	g.drawImage(image, 0,0 , getWidth(), getHeight(),null);
	    		
    	//drawing operations end
    	g.dispose();
    	bs.show();
    }
    
    public static void main(String[] args){

    	Game game = new Game();
    	
    	game.frame.setResizable(false);
    	game.frame.setTitle("The Ore");
    	game.frame.add(game);
    	game.frame.pack();
    	game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	game.frame.setLocationRelativeTo(null);
    	game.frame.setVisible(true);
    	
    	game.start();
    }
}
