package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel  implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16px x 16px tiles
    final int scale = 3; //used to scale pixels to different resolutions
    public final int tileSize = originalTileSize * scale; //48px x 48px tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //World Settings
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 15;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;

    //Setup TileManager
    TileManager tileManager = new TileManager(this);
    //Setup main.KeyHandler
    KeyHandler keyHandler =new KeyHandler();

    //Setup Game Time
    Thread gameThread; //keeps things going at a constant framerate

    //Instantiate Player
    public Player player = new Player(this, keyHandler);

    //Create Game Panel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set window size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //better rendering performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //Game Panel is always ready to receive input
    }

    //Create Game Thread for Timing(s)
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Create Delta Game Loop
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //nanosecods divided by 60 frames per second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        //Runs the Game Loop
        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000)  {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }//Game Loop: Sleep method - update -> repaint -> update -> repaint - on and on
        /*    //Maintain framerate
            long currenTime = System.nanoTime();

            //Update Information - e.g. character positions (occurs x times, x == framerate)
            update();

            //Draw the Screen (occurs x times, x == framerate)
            repaint(); //calls java's paintComponent method

            //Find remaining time until next draw time. Game with "sleep" until next draw time
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; //milliseconds

                if (remainingTime < 0) { //No time is left in the draw time interval
                    remainingTime = 0;
                }
                //Sleep the Game Loop until appropriate time
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; //setup next draw interval
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        } */
    }
    //Update Player Position
    public void update() {
        player.update();
    }

    //Draw onto JPanel
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D graphic2 = (Graphics2D)graphic; //get more precise control over graphics

        //draw tile
        tileManager.draw(graphic2); //needs to be layer under player

        //draw player
        player.draw(graphic2);

        graphic2.dispose(); //release any resources being used
    }
}
