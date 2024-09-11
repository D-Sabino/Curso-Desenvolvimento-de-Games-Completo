package inicio_logica_games;

import java.util.ArrayList;

public class Game implements Runnable {
	private boolean isRunning;
	private Thread thread;
	
	private ArrayList<Entidade> entidades = new ArrayList();
	
	public Game() {
		entidades.add(new Entidade());
		
		
		for(int i = 0; i < entidades.size(); i++) {
			Entidade e = entidades.get(i);
		}
	}
	
	public static void main (String[] args) {
		Game game = new Game();
		game.start();
		
		
	}
	
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void tick() {
		//Atualizar o jogo
		System.out.println("Tick");
	}
	
	public void render() {
		//Renderizar o jogo
		System.out.println("Render");
	}
	
	
	@Override
	public void run() {
		while(isRunning) {
			tick();
			render();
		}
	}
	
	
}
