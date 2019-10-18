package com.neulii.theOre;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private int width = 300;
    private int height = width / 16 * 9;

    private boolean running = false;

    private  int scale = 3;

    private Thread thread;

    private JFrame frame;

    public Game(){
    	
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        frame = new JFrame();
        

    }
    public void start(){

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
        while(running){

        	update(); //60 ticks per second
        	render(); //fast as possible
        }

    }
    
    public void update() {
    	
    	
    }
    
    public void render() {
    	
    	BufferStrategy bs = getBufferStrategy();
    	
    	if(bs==null) {
    		createBufferStrategy(3);
    		return;
    	}
    	
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
