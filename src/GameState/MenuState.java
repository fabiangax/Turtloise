package GameState;

import Audio.AudioPlayer;
import TurtloiseMaps.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MenuState extends GameState {
	private Background bg;
	private int currentChoice = 0;
	private String[] options = {
		"start",
		"controls",
		"exit"
	};
        private BufferedImage image;
        AudioPlayer currentMusic;

        public Font getFont() {
            try {
                InputStream is = MenuState.class.getResourceAsStream("/Fonts/MKDS.ttf");
                Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                Font sizedFont = font.deriveFont(25f);
                return sizedFont;
            }
            catch (FontFormatException | IOException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        public Image getImage() {
            try {
                image = ImageIO.read(new File("src/Graficos/Backgrounds/titulo.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                return image;
            }
        }
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new Background("/Graficos/Backgrounds/fondo_menu.png", 1);
                        bg.setVector(-0.2, 0);
		}
		catch(Exception e) {
			e.printStackTrace();
                }
                init();
	}
	public void init() {
            //currentMusic = new AudioPlayer("/Graficos/Music/intro.mp3");
            //currentMusic.play();
        }
	public void update() {
                bg.update();
	}
        public GameStateManager getGSM(){
            return gsm;
        }
	public void draw(Graphics2D g) {
		// draw bg
		bg.draw(g);
		// draw title
		g.drawImage(getImage(),0,0,null);
		// draw menu options
		g.setFont(getFont());
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.ORANGE);
			}
			else {
				g.setColor(Color.BLACK);
			}
                        switch(i){
                            case 0:
                                g.drawString(options[i], 230, 170 + i * 25);
                                break;
                            case 1:
                                g.drawString(options[i], 205, 170 + i * 25);
                                break;
                            case 2:
                                g.drawString(options[i], 238, 170 + i * 25);
                                break;
                        }
		}
	}
	private void select() {
		if(currentChoice == 0) {
                        //currentMusic.stop();
			//gsm.setState(GameStateManager.LEVEL1STATE);
                        SelectLevel levels = new SelectLevel(gsm);
                        levels.setVisible(true);
		}
		if(currentChoice == 1) {
			Controles controles = new Controles();
                        controles.setVisible(true);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
}










