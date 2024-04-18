package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
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
        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }

        // Update sprite counter
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
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
}
