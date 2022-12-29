import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    int playerPositionX;
    int playerPositionY;
    private int playerSpeed = 3;
    private BufferedImage playerImage;
    final int cameraX;
    final int cameraY;


    Panel panel;
    KeyEventHandler keyX;

    public Player(Panel panel, KeyEventHandler keyX) {
        this.panel = panel;
        this.keyX = keyX;
        // set camera on player in game
        cameraX = panel.screenWidth/2 - (panel.finalTileSize/2);
        cameraY = panel.screenHeight/2 - (panel.finalTileSize/2);
        //set player position
        playerPositionX = panel.finalTileSize * 8;
        playerPositionY = panel.finalTileSize * 10;
    }

    public void sync() {    //update user positions on each frame
        if (keyX.goingUp){
            playerPositionY -= playerSpeed;
        }
        if (keyX.goingDown){
            playerPositionY += playerSpeed;
        }
        if (keyX.goingLeft){
            playerPositionX -= playerSpeed;
        }
        if (keyX.goingRight){
            playerPositionX += playerSpeed;
        }
    }

    public void draw(Graphics2D graphics2D){   //create game character
        //draw player
        getPlayerImage();
        graphics2D.drawImage(playerImage, cameraX, cameraY, panel.finalTileSize, panel.finalTileSize, null);
    }

    //getters and setters
    public void getPlayerImage() {
        try {
            if (keyX.goingLeft) {
                playerImage = ImageIO.read(getClass().getResourceAsStream("normal.png"));
            } else if (keyX.goingRight) {
                playerImage = ImageIO.read(getClass().getResourceAsStream("right.png"));
            } else {
                playerImage = ImageIO.read(getClass().getResourceAsStream("normal.png"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
}
