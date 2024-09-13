package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class ball {
	public double x, y, dx, dy, speed = 1.6;
	public int width, height;

	
	public ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if( (x + (dx * speed) + width >= game.WIDTH) || (x + (dx * speed) < 0) )
			dx *= -1;
		
		if(y >= game.HEIGHT) {
			System.out.println("Ponto do inimigo!");
			new game();
			return;
		}
		else if (y < 0) {
			System.out.println("Nosso ponto!");
			new game();
			return;
		}
			
		Rectangle bounds = new Rectangle( (int)(x + (dx * speed)), (int)(y + (dy * speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(game.player.x, game.player.y, game.player.width, game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)game.enemy.x, (int)game.enemy.y, game.enemy.width, game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
				dy*=-1;
		}else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0)
				dy*=-1;
		}
			

		x += dx * speed;
		y += dy * speed;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}
}
