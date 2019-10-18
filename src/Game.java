import java.awt.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private int width = 300;
    private int height = width / 16 * 9;

    private boolean running = false;

    public int scale = 3;

    Thread thread;

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

        	System.out.println("running....");
        	
        }

    }


    public static void main(String[] args){

    	Game game = new Game();
    	game.start();

    }


}
