import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static Image gif_background;

    public static void main(String[] args) {
        JFrame frame = new JFrame("The Floor is Lava");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to maximize
        frame.setUndecorated(true); // Set the frame to undecorated
        frame.setResizable(false); // Disable resizing6
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load GIF
        gif_background = new ImageIcon(Main.class.getResource("/assets/background_1.gif")).getImage();

        Main main = new Main();

        // Create a JPanel to hold the card content
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(20, 20, 200, 300);

        main.add(cardPanel);
        frame.add(main);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw GIF
        if (gif_background != null) {
            g.drawImage(gif_background, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw a semi-transparent rectangle
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f)); // Set opacity to 80%
        g2d.fillRect(50, 50, 500, 900);
        g2d.dispose();

        String titlePart1 = "THE FLOOR IS";
        String titlePart2 = "LAVA";
        g.setColor(new Color(152, 255, 152));
        try {
            // Load the Custom fonts
            // Created separate InputStreams for each font to avoid closing the same InputStream multiple times
            InputStream is_1 = Main.class.getResourceAsStream("/assets/fonts/PixelifySans-VariableFont_wght.ttf");
            Font title_font = Font.createFont(Font.TRUETYPE_FONT, is_1).deriveFont(Font.BOLD, 65);
            InputStream is_2 = Main.class.getResourceAsStream("/assets/fonts/PixelifySans-VariableFont_wght.ttf");
            Font button_font = Font.createFont(Font.TRUETYPE_FONT, is_2).deriveFont(Font.BOLD, 40);
            InputStream is_3 = Main.class.getResourceAsStream("/assets/fonts/Kanit-Italic.ttf");
            Font footer_font = Font.createFont(Font.TRUETYPE_FONT, is_3).deriveFont(Font.BOLD, 20);
            
            g.setFont(title_font);
            FontMetrics fm_title = g.getFontMetrics(title_font);

            // Draw "The Floor is"
            int textWidth1 = fm_title.stringWidth(titlePart1);
            int x1 = 50 + (500 - textWidth1) / 2;
            int y1 = 80 + fm_title.getAscent();
            g.drawString(titlePart1, x1, y1);

            // Draw "Lava" below
            int textWidth2 = fm_title.stringWidth(titlePart2);
            int x2 = 50 + (500 - textWidth2) / 2;
            int y2 = y1 + fm_title.getHeight();
            g.drawString(titlePart2, x2, y2);

            // Draw buttons
            String[] buttons = { "START GAME", "SETTINGS", "QUIT" };
            int startY = y2 + 200;
            g.setFont(button_font);
            FontMetrics fm_button = g.getFontMetrics(button_font);
            
            for (String text : buttons) {
                int textWidth = fm_button.stringWidth(text);
                int x = 50 + (500 - textWidth) / 2;
                int y = startY + fm_button.getAscent();
                g.drawString(text, x, y);
                Rectangle clickableArea = new Rectangle(x, y - fm_button.getAscent(), textWidth, fm_button.getHeight());

                // Add mouse listener to detect clicks on the text
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (clickableArea.contains(e.getPoint())) {
                            // Handle click action here
                            handleClickAction(text);
                        }
                    }
                });

                startY += fm_button.getHeight() + 50; // Add some spacing between each text
            }

            // Draw the footer text
            g.setFont(footer_font);
            FontMetrics fm_footer = g.getFontMetrics(footer_font);
            String footerText = "CMSC 137 B-4L GROUP 5";
            int footerTextWidth = fm_footer.stringWidth(footerText);
            int footerX = 50 + (500 - footerTextWidth) / 2;
            int footerY = fm_button.getHeight() + startY + 125 + fm_footer.getAscent();
            g.drawString(footerText, footerX, footerY);

            Rectangle clickableAreaFooter = new Rectangle(footerX, footerY - fm_footer.getAscent(), footerTextWidth, fm_footer.getHeight());

            // Add mouse listener to detect clicks on the footer text
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (clickableAreaFooter.contains(e.getPoint())) {
                        // Handle click action here
                        handleClickAction(footerText);
                    }
                }
            });

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Handle title_font loading error
        }
    }

    // Method to handle click actions for each buttons
    private void handleClickAction(String text) {
        switch (text) {
            case "START GAME":
                System.out.println("Starting the game...");
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
}
