package Entity.Enemies;

import Entity.*;
import TurtloiseMaps.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;

import javax.imageio.ImageIO;

public class Capodrilo extends Enemy2 {
	public boolean capodrilo=true;
	private BufferedImage[] sprites;
	
	public Capodrilo(TileMap tm) {
		
		super(tm);
                
		moveSpeed = 1.0;
		maxSpeed = 2.0;
		fallSpeed = 0.2;    
		maxFallSpeed = 10;
		
		width = 80;
		height = 64;
		cwidth = 60;
		cheight = 40;
		
		health = maxHealth = 5;
		damage = 1;
		//get type
                
		// load sprites
		try {
                        BufferedImage spritesheet = ImageIO.read(
                                    getClass().getResourceAsStream(
                                            "/Graficos/Sprites/Enemies/capodrilo.png"
                                    )
                            );

			sprites = new BufferedImage[6];
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
		
		right = true;
		facingRight = false;
		
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		
		// falling
		if(falling) {
			dy += fallSpeed;
		}
		
	}
	
	public void update() {
		
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
		if(right && dx == 0) {
			right = false;
			left = true;
			facingRight = true;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingRight = false;
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