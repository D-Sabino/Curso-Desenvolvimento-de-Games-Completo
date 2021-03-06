package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	//Defini??es de tamanho da janela
	public static int WIDTH = 240;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public Player player;
	
	
	public Game() {
		//Seta o tamanho da janela
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		
		player = new Player(100, HEIGHT-10);
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong"); //Cria a janela do jogo.
		frame.setResizable(false); //N?o permite que a janela seja redimensionada
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando fecha a aplica??p, ela ? finalizada
		frame.add(game); 
		frame.pack();
		frame.setLocationRelativeTo(null); //Centraliza a janela
		frame.setVisible(true);	
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
	}
	
	public void render () {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		
		}
		
		Graphics g = layer.getGraphics();
		//Limpa a tela
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bs.show();
	}
	
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = true;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = true;
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = false;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = false;
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
