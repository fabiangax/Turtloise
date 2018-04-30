package Entity.Enemies;

import Entity.*;
import TurtloiseMaps.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;

import javax.imageio.ImageIO;

public class Jelly extends Enemy {
	
	private BufferedImage[] sprites;
	
	public Jelly(TileMap tm) {
		
		super(tm);
                
		moveSpeed = 1.0;
		maxSpeed = 2.0;
		fallSpeed = 0.1;    
		maxFallSpeed = 0.2;
		
		width = 16;
		height = 16;
		cwidth = 14;
		cheight = 14;
		
		health = maxHealth = 1;
		damage = 1;
		//get type
                Random rnd = new Random();
                int type = (int)(rnd.nextDouble() * 3 + 1);
		// load sprites
		try {
                        BufferedImage spritesheet=null;
                        if(type==1){
                            spritesheet = ImageIO.read(
                                    getClass().getResourceAsStream(
                                            "/Graficos/Sprites/Enemies/medusa_roja.png"
                                    )
                            );
                        }
                        else{
                            if(type==2){
                                spritesheet = ImageIO.read(
                                        getClass().getResourceAsStream(
                                                "/Graficos/Sprites/Enemies/medusa_azul.png"
                                        )
                                );
                            }
                            else{
                                if(type==3){
                                spritesheet = ImageIO.read(
                                        getClass().getResourceAsStream(
                                                "/Graficos/Sprites/Enemies/medusa_rosa.png"
                                        )
                                );
                            }
                            }
                        }			
			sprites = new BufferedImage[8];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
	}
	
	private void getNextPosition() {
		
		if(falling) {
                    dy += fallSpeed;
		}
                else{
                    dy -= 4;
                }
		
	}
	
	public void update() {
		          System.out.println("dx:"+dx+"-dy:"+dy);
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check flinching
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}
		
		// if it hits a wall, go other direction
		if(up && dy == 0) {
                    dy--;
		}
                else{
                    if(down && dy==0) {
                        dy++;
                    }
		}
		
		// update animation
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
}












