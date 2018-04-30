package Entity;

import GameState.GameStateManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {
	
	private Player player;
	private BufferedImage image;
	private Font font;
	GameStateManager gsm = new GameStateManager();
        
	public HUD(Player p) {
		player = p;
	}
	
	public void draw(Graphics2D g) {
		switch(player.getHealth()){
                    case 1:
                        try {
                            image = ImageIO.read(
                                    getClass().getResourceAsStream(
                                            "/Graficos/HUD/hud_franklin3.png"
                                    )
                            );
                            g.drawImage(image, 0, 10, null);
                        }
                        catch(Exception e) {
			e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            image = ImageIO.read(
                                    getClass().getResourceAsStream(
                                            "/Graficos/HUD/hud_franklin2.png"
                                    )
                            );
                            g.drawImage(image, 0, 10, null);
                        }
                        catch(Exception e) {
			e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            image = ImageIO.read(
                                    getClass().getResourceAsStream(
                                            "/Graficos/HUD/hud_franklin1.png"
                                    )
                            );
                            g.drawImage(image, 0, 10, null);
                        }
                        catch(Exception e) {
			e.printStackTrace();
                        }
                        break;
                }

	}
}













