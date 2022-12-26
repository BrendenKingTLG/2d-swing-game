import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private int positionX = 100;
    private int positionY = 100;
    private int playerSpeed = 3;
    private BufferedImage playerImage;


    Panel panel;
    KeyEventHandler keyX;

    public Player(Panel panel, KeyEventHandler keyX) {
        this.panel = panel;
        this.keyX = keyX;
    }

    public void sync() {    //update user positions on each frame
        if (keyX.goingUp){
            positionY -= playerSpeed;
        }
        if (keyX.goingDown){
            positionY += playerSpeed;
        }
        if (keyX.goingLeft){
            positionX -= playerSpeed;
        }
        if (keyX.goingRight){
            positionX += playerSpeed;
        }
    }

    public void draw(Graphics2D graphics2D){   //create game character
        //draw player
        getPlayerImage();
        graphics2D.drawImage(playerImage, positionX, positionY, panel.finalTileSize, panel.finalTileSize, null);
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

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
}
