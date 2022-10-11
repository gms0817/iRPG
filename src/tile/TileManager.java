package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10]; //can adjust as needed
        mapTileNum = new int[gamePanel.maxScreenCol][ gamePanel.maxScreenRow]; //will hold map data from map txt file
        getTileImage();
        loadMap("/maps/map01.txt"); //loads map01
    }

    public void getTileImage() {
        try { //link tiles to the tile array
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try {
            InputStream input = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            int col = 0, row = 0;
            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = br.readLine(); //reads line of text

                while(col < gamePanel.maxScreenCol) {
                    String numbers[] = line.split(" "); //splits string at each space using regex
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Draw tiles to the map from text file
    public void draw(Graphics2D graphics2D) {
        int col = 0, row = 0, x = 0, y = 0;
        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
            int tileNum = mapTileNum[col][row]; //extract tile number from array and store to draw it
            graphics2D.drawImage(tile[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
