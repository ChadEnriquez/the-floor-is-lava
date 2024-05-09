package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Rectangle;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    private int lives;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        lives = 3;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 24;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../assets/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Check if any directional key is pressed
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // Check collision
            collisionOn = false;
            gp.cc.checkTile(this);

            // If collision is not detected, move the player
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }

                if ((boolean) gp.cc.isOnLavaTile(this)) {
                    lives--;
                    if (lives == 0) {
                        // Character Death
                    }
                }
            }

            // Update sprite counter
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        } else if (keyH.escapePressed) {
            // Perform action
            System.exit(0);
        }
    }

    public void draw(Graphics2D g2) {
        // Draw player image
        BufferedImage playerImage = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    playerImage = up1;

                }
                if (spriteNum == 2) {
                    playerImage = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    playerImage = down1;
                }
                if (spriteNum == 2) {
                    playerImage = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    playerImage = left1;
                }
                if (spriteNum == 2) {
                    playerImage = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    playerImage = right1;
                }
                if (spriteNum == 2) {
                    playerImage = right2;
                }
                break;
        }
        g2.drawImage(playerImage, x, y, gp.tileSize, gp.tileSize, null);
    }

    public int getLives() {
        System.out.println("Player lives: " + lives);
        return lives;
    }

    public void loseLife() {
        lives--;
        System.out.println("Player lives: " + lives);
    }
}
