package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javafx.util.*;

public class Main extends JPanel {
    private static final String[] BUTTON_TEXTS = { "START GAME", "SETTINGS", "QUIT" };
    private static final String FOOTER_TEXT = "CMSC 137 B-4L GROUP 5";

    private Image gifBackground;
    private Font titleFont;
    private Font buttonFont;
    private Font footerFont;
    private List<Pair<Rectangle, String>> clickableAreas;

    public Main() {
        setBackground(Color.WHITE);
        loadResources();
        setupClickableAreas();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getPoint());
            }
        });
    }

    private void loadResources() {
        try {
            gifBackground = new ImageIcon(Main.class.getResource("/assets/background_1.gif")).getImage();
            InputStream is1 = Main.class.getResourceAsStream("/assets/fonts/PixelifySans-VariableFont_wght.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.BOLD, 65);
            InputStream is2 = Main.class.getResourceAsStream("/assets/fonts/PixelifySans-VariableFont_wght.ttf");
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.BOLD, 40);
            InputStream is3 = Main.class.getResourceAsStream("/assets/fonts/Kanit-Italic.ttf");
            footerFont = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.BOLD, 20);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Handle resource loading error
        }
    }

    private void setupClickableAreas() {
        clickableAreas = new ArrayList<>();
        FontMetrics fmButton = getFontMetrics(buttonFont);
        int startY = 450; // Adjust this value as needed

        for (String text : BUTTON_TEXTS) {
            int textWidth = fmButton.stringWidth(text);
            int x = 50 + (500 - textWidth) / 2;
            int y = startY + fmButton.getAscent();
            Rectangle clickableArea = new Rectangle(x, y - fmButton.getAscent(), textWidth, fmButton.getHeight());
            clickableAreas.add(new Pair<>(clickableArea, text));
            startY += fmButton.getHeight() + 50;
        }

        // Add clickable area for the footer
        FontMetrics fmFooter = getFontMetrics(footerFont);
        int footerTextWidth = fmFooter.stringWidth(FOOTER_TEXT);
        int footerX = 50 + (500 - footerTextWidth) / 2;
        int footerY = 1120;
        Rectangle footerClickableArea = new Rectangle(footerX, footerY - fmFooter.getAscent(), footerTextWidth, fmFooter.getHeight());
        clickableAreas.add(new Pair<>(footerClickableArea, FOOTER_TEXT));
    }

    private void handleClick(Point point) {
        for (Pair<Rectangle, String> pair : clickableAreas) {
            if (pair.getKey().contains(point)) {
                handleButtonClick(pair.getValue());
                return;
            }
        }
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "START GAME":
                System.out.println("Starting the game...");

                JFrame start_game_window = new JFrame();
                start_game_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                start_game_window.setResizable(false); // Disable resizings
                start_game_window.setUndecorated(true);
                start_game_window.setTitle("The Floor is Lava");
                start_game_window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the start_game_window to maximize

                GamePanel gamePanel = new GamePanel();
                start_game_window.add(gamePanel);
                start_game_window.pack();
                start_game_window.setLocationRelativeTo(null);
                start_game_window.setVisible(true);

                gamePanel.startGameThread();

                break;
            case "SETTINGS":
                System.out.println("Opening settings...");
                break;
            case "QUIT":
                System.out.println("Quit the game...");
                System.exit(0);
                break;
            case "CMSC 137 B-4L GROUP 5":
                System.out.println("Developed by CMSC 137 B-4L Group 5");
                break;
            default:
                System.out.println("Unknown action");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawTitle(g);
        drawButtons(g);
        drawFooter(g);
    }

    private void drawBackground(Graphics g) {
        if (gifBackground != null) {
            g.drawImage(gifBackground, 0, 0, getWidth(), getHeight(), this);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 204)); // Semi-transparent black
        g2d.fillRect(50, 50, 500, 900);
        g2d.dispose();
    }

    private void drawTitle(Graphics g) {
        g.setColor(new Color(152, 255, 152));
        g.setFont(titleFont);
        FontMetrics fmTitle = g.getFontMetrics(titleFont);
        int textWidth1 = fmTitle.stringWidth("THE FLOOR IS");
        int textWidth2 = fmTitle.stringWidth("LAVA");
        int x1 = 50 + (500 - textWidth1) / 2;
        int x2 = 50 + (500 - textWidth2) / 2;
        g.drawString("THE FLOOR IS", x1, 130);
        g.drawString("LAVA", x2, 220);
    }

    private void drawButtons(Graphics g) {
        g.setFont(buttonFont);
        FontMetrics fmButton = g.getFontMetrics(buttonFont);
        for (Pair<Rectangle, String> pair : clickableAreas) {
            Rectangle rect = pair.getKey();
            String text = pair.getValue();
            int textWidth = fmButton.stringWidth(text);
            int x = rect.x + (rect.width - textWidth) / 2;
            int y = rect.y + fmButton.getAscent();
            g.drawString(text, x, y);
        }
    }

    private void drawFooter(Graphics g) {
        g.setFont(footerFont);
        FontMetrics fmFooter = g.getFontMetrics(footerFont);
        int textWidth = fmFooter.stringWidth(FOOTER_TEXT);
        int x = 50 + (500 - textWidth) / 2;
        int y = 900 + fmFooter.getAscent();
        g.drawString(FOOTER_TEXT, x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("The Floor is Lava");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setUndecorated(true);
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            Main main = new Main();
            window.add(main);
            window.setVisible(true);
        });
    }
}