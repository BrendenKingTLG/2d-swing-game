import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {

    Panel panel;
    Tile[] tile;


    public TileManager(Panel panel){
        this.panel = panel;
        tile = new Tile[10];
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("ocean.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("sand.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("ocean.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D){
        getTileImage();
        graphics2D.drawImage(tile[0].image, 100, 100, panel.finalTileSize, panel.finalTileSize, null);
        }
    }
