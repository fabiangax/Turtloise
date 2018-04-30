package GameState;
import Audio.AudioPlayer;

public class GameStateManager {
	private GameState[] gameStates;
	private int currentState;
	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
        public static final int LEVEL2STATE = 2;
        private AudioPlayer currentMusic;
	public GameStateManager() {
		gameStates = new GameState[NUMGAMESTATES];
		currentState = MENUSTATE;
		loadState(currentState);
	}
	private void loadState(int state) {
		if(state == MENUSTATE){
			gameStates[state] = new MenuState(this);                        
                }
		if(state == LEVEL1STATE){
			gameStates[state] = new Level1State(this);
                        //currentMusic.stop();
                        /*currentMusic.stop();
                        currentMusic.close();
                        currentMusic1 = new AudioPlayer("/Graficos/Music/level1-1.mp3");
                        currentMusic1.play();*/
                }
                if(state == LEVEL2STATE){
			gameStates[state] = new Level2State(this);
                }
	}
	
	public void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		//gameStates[currentState].init();
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
	
}









