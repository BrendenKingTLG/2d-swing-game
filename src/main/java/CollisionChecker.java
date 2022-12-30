public class CollisionChecker {
    Panel panel;

    public CollisionChecker(Panel panel) {
        this.panel = panel;
    }

    public void checkTile(Player player){
        int playerLeft = player.playerPositionX + player.solidArea.x;
        int playerRight = player.playerPositionX + player.solidArea.x + player.solidArea.width;
        int playerTop = player.playerPositionY + player.solidArea.y;
        int playerBottom = player.playerPositionY + player.solidArea.y + player.solidArea.height;

        int playerColLeft = playerLeft/panel.finalTileSize;
        int playerColRight = playerRight/panel.finalTileSize;
        int playerRowTop = playerTop/ panel.finalTileSize;
        int playerRowBottom = playerBottom/panel.finalTileSize;

        int tileNum1, tileNum2;

        switch (player.travelingDirection) {
            case "up":
                playerRowTop = (playerTop - player.playerSpeed)/panel.finalTileSize;
                tileNum1 = panel.tileManager.mapTiles[playerColLeft][playerRowTop];
                tileNum2 = panel.tileManager.mapTiles[playerColRight][playerRowTop];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;
            case "down":
                playerRowBottom = (playerBottom + player.playerSpeed)/panel.finalTileSize;
                tileNum1 = panel.tileManager.mapTiles[playerColLeft][playerRowBottom];
                tileNum2 = panel.tileManager.mapTiles[playerColRight][playerRowBottom];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;
            case "left":
                playerColLeft = (playerLeft - player.playerSpeed)/panel.finalTileSize;
                tileNum1 = panel.tileManager.mapTiles[playerColLeft][playerRowTop];
                tileNum2 = panel.tileManager.mapTiles[playerColLeft][playerRowBottom];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;
            case "right":
                playerColRight = (playerRight + player.playerSpeed)/panel.finalTileSize;
                tileNum1 = panel.tileManager.mapTiles[playerColRight][playerRowTop];
                tileNum2 = panel.tileManager.mapTiles[playerColRight][playerRowBottom];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    player.collisionOn = true;
                }
                break;
        }
    }
}
