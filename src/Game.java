import java.awt.*;

public class Game extends Canvas implements Runnable {

    private int width = 300;
    private int height = width /16 * 9;

    private boolean running = false;

    public int scale = 3;

    Thread thread;

    public void start(){
        thread = new Thread(this, "Display");
        thread.start();

    }

    public void stop(){
        try{
            thread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while(running){

        }

    }


    public static void main(String[] args){



    }


}
