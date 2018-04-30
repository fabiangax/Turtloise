package GameState;

import Audio.AudioPlayer;
import Main.GamePanel;
import Entity.*;
import Entity.Enemies.Capodrilo;
import Entity.Enemies.Jelly;
import Entity.Enemies.Slugger;
import Entity.Objects;
import Entity.Enemies.Transformer;
import TurtloiseMaps.Background;
import TurtloiseMaps.TileMap;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level2State extends GameState {
        private boolean canJump=true;
    
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
        private AudioPlayer bgMusic;
        
	private ArrayList<Enemy> enemies;
        private ArrayList<Enemy2> enemies2;
        private ArrayList<Objects> objects;
	private ArrayList<Explosion> explosions;
	
	private HUD hud;
	
	//AudioPlayer currentMusic2;

        
	public Level2State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Graficos/Tilesets/sprite_mundo.png");
		//tileMap.loadMap("/Graficos/Maps/pruebamarina.map");
                //tileMap.loadMap("/Graficos/Maps/tilepruebas.map");
                tileMap.loadMap("/Graficos/Maps/level2_v3.map");
                //tileMap.loadMap("/Graficos/Maps/avereste.map");
                //tileMap.loadMap("/Graficos/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
                bg = new Background("/Graficos/Backgrounds/fondo_mundo.png", 0.2);
		
		player = new Player(tileMap);
		player.setPosition(100, 250);
		
		populateEnemies();
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
                
                bgMusic = new AudioPlayer("/Graficos/Music/level1-2.mp3");
		bgMusic.play();

		
	}
	
	private void populateEnemies() {
		enemies = new ArrayList<Enemy>();
		enemies2 = new ArrayList<Enemy2>();
                Slugger s;
		
                Point[] points = new Point[] {
			new Point(1080, 54),
                        new Point(980, 313),
                        new Point(1813, 414),
                        new Point(2985, 186)
		};
		
                for(int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
                
                Jelly j;
		
                Point[] points2 = new Point[] {
			new Point(4800, 330),
                        new Point(3850, 332),
                        new Point(2600, 192),
                        new Point(1032, 318)
		};
		
                for(int i = 0; i < points2.length; i++) {
			j = new Jelly(tileMap);
			j.setPosition(points2[i].x, points2[i].y);
			enemies.add(j);
		}
                
                Capodrilo cap;
                
                Point[] points4= new Point[] {
			new Point(5600, 280)
		};
		
                for(int i = 0; i < points4.length; i++) {
			cap = new Capodrilo(tileMap);
			cap.setPosition(points4[i].x, points4[i].y);
			enemies2.add(cap);
		}
                
                objects = new ArrayList<Objects>();
		
		Transformer t;
                
		Point[] points3 = new Point[] {
			/*new Point(140, 236),
                        new Point(250, 236)*/
		};
		for(int i = 0; i < points3.length; i++) {
			t = new Transformer(tileMap);
			t.setPosition(points3[i].x, points3[i].y);
			objects.add(t);
		}
	}
	public void update() {
		
		// update player
		player.update();
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getx(),
			GamePanel.HEIGHT / 2 - player.gety()
		);
		
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety()-1100);
		
		// attack enemies
		player.checkAttack(enemies);
		player.checkObjects(objects);
                player.checkAttack2(enemies2);
		// update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
                                //if(e.)
			}
		}
                
                for(int i = 0; i < enemies2.size(); i++) {
			Enemy2 e = enemies2.get(i);
			e.update();
			if(e.isDead()) {
				enemies2.remove(i);
				i--;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
                                //if(e.)
                                Final finalImg = new Final();
                                finalImg.setVisible(true);
                                finalImg.setLocationRelativeTo(null);
			}
		}
                

                for(int i = 0; i < objects.size(); i++) {
			Objects o = objects.get(i);
			o.update();
		}
		
		// update explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
                if(player.isDead()) {
                    gsm.setState(GameStateManager.MENUSTATE);
                    SelectLevel levels = new SelectLevel(gsm);
                    levels.setVisible(true);
		}
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
                for(int i = 0; i < enemies2.size(); i++) {
			enemies2.get(i).draw(g);
		}
		
                // draw transformer bar
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}
                
		// draw explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
                
		// draw hud
		hud.draw(g);
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W&&canJump){
                    canJump=false;
                    player.setJumping(true);
                }
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W){
                    canJump=true;
                    player.setJumping(false);
                }
		if(k == KeyEvent.VK_E) player.setGliding(false);
	}
	
}