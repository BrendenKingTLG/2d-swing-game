import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    int playerPositionX;
    int playerPositionY;
    int playerSpeed = 3;
    private BufferedImage playerImage;
    final int cameraX;
    final int cameraY;
    Rectangle solidArea;
    boolean collisionOn = false;
    String travelingDirection = "not moving";

    Panel panel;
    KeyEventHandler keyX;

    public Player(Panel panel, KeyEventHandler keyX) {
        this.panel = panel;
        this.keyX = keyX;
        // set camera on player in game
        cameraX = panel.screenWidth/2 - (panel.finalTileSize/2);
        cameraY = panel.screenHeight/2 - (panel.finalTileSize/2);
        //set player position
        playerPositionX = panel.finalTileSize * 25;
        playerPositionY = panel.finalTileSize * 29;
        solidArea = new Rectangle(8, 16, 32, 32); // rect sets bounds for collision
    }

    public void sync() {    //update user positions on each frame
        if (keyX.goingUp){
            travelingDirection = "up";

        }
        if (keyX.goingDown){
            travelingDirection = "down";

        }
        if (keyX.goingLeft){
            travelingDirection = "left";

        }
        if (keyX.goingRight){
            travelingDirection = "right";
        }

        //check collision
        collisionOn = false;
        panel.collisionChecker.checkTile(panel.player);

        if (!collisionOn){
            switch (travelingDirection){
                case "up":
                    playerPositionY -= playerSpeed;
                    break;
                case "down":
                    playerPositionY += playerSpeed;
                    break;
                case "left":
                    playerPositionX -= playerSpeed;
                    break;
                case "right":
                    playerPositionX += playerSpeed;
                    break;
                case "not moving":
                    break;
            }
        }
        travelingDirection = "not moving";
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
                playerImage = ImageIO.read(getClass().getResourceAsStream("playerLeft.png"));
            } else if (keyX.goingRight) {
                playerImage = ImageIO.read(getClass().getResourceAsStream("playerRight.png"));
            } else if (keyX.goingUp){
                playerImage = ImageIO.read(getClass().getResourceAsStream("playerBack.png"));
            }else {
                playerImage = ImageIO.read(getClass().getResourceAsStream("playerFront.png"));
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
