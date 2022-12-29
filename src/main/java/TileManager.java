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
    }

    public void getTileImage(){
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/ocean.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sand.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap() throws IOException {
        try {
            mapTiles = new int[20][16];
            InputStream im = getClass().getResourceAsStream("/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(im));

            int col = 0;
            int row = 0;



            while (col < panel.maxWindowCol && row < panel.maxWindowRow) {
                String line = br.readLine();
                while (col < panel.maxWindowCol) {
                    String[] nums = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    mapTiles[col][row] = num;
                    col++;
                }
                if (col == panel.maxWindowCol){
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        try {
            while (col < panel.maxWindowCol && row < panel.maxWindowRow) {
                int tileNumber = mapTiles[col][row];
                graphics2D.drawImage(tile[tileNumber].image, x, y, panel.finalTileSize, panel.finalTileSize, null);
                col++;
                x += panel.finalTileSize;

                if (col == panel.maxWindowCol) {
                    col = 0;
                    x = 0;
                    row ++;
                    y += panel.finalTileSize;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }
