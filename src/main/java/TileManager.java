import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {

    Panel panel;
    Tile[] tile;

    public TileManager(Panel panel){
        this.panel = panel;
        tile = new Tile[10];
        getTileImage();
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

    public void paint(Graphics2D graphics2D){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col <= panel.maxWindowCol && row <= panel.maxWindowRow){

            graphics2D.drawImage(tile[0].image, x, y, panel.finalTileSize, panel.finalTileSize, null);
            col++;
            x += panel.finalTileSize;

            if (col == panel.maxWindowCol) {
                col = 0;
                x = 0;
                row++;
                y += panel.finalTileSize;
            }
        }
    }
}
