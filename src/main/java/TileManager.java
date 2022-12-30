import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    Panel panel;
    Tile[] tile = new Tile[10];;
    int[][] mapTiles;


    public TileManager(Panel panel) throws IOException {
        this.panel = panel;
//        getTileImage();
//        loadMap();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].collision = true;
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/ocean.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sand1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap() throws IOException {
        try {
            mapTiles = new int[50][50]; //comes from panel world size
            InputStream im = getClass().getResourceAsStream("/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(im));

            int col = 0;
            int row = 0;



            while (col < panel.maxWorldCol&& row < panel.maxWorldRow) {
                String line = br.readLine();
                while (col < panel.maxWorldCol) {
                    String[] nums = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    mapTiles[col][row] = num;
                    col++;
                }
                if (col == panel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void draw(Graphics2D graphics2D) throws IOException {
        getTileImage();
        loadMap();
        int worldCol = 0;
        int worldRow = 0;

        try {
            while (worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow) {
                int tileNumber = mapTiles[worldCol][worldRow];

                //world is for map, camera is for player display area
                int worldX = worldCol * panel.finalTileSize;
                int worldY = worldRow * panel.finalTileSize;
                int cameraX = worldX - panel.player.playerPositionX + panel.player.cameraX;
                int cameraY = worldY - panel.player.playerPositionY + panel.player.cameraY;
                if (worldX + panel.finalTileSize > panel.player.playerPositionX - panel.player.cameraX &&
                    worldX - panel.finalTileSize < panel.player.playerPositionX + panel.player.cameraX &&
                    worldY + panel.finalTileSize > panel.player.playerPositionY - panel.player.cameraY &&
                    worldY - panel.finalTileSize < panel.player.playerPositionY + panel.player.cameraY) {
                    graphics2D.drawImage(tile[tileNumber].image, cameraX, cameraY, panel.finalTileSize, panel.finalTileSize, null);

                }
                worldCol++;

                if (worldCol == panel.maxWorldCol) {
                    worldCol = 0;
                    worldRow ++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }
