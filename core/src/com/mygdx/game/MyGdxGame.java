package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.GameEngine.AssetManager.AudioSettings;
import com.mygdx.game.GameEngine.BehaviourManager.BehaviourManager;
import com.mygdx.game.GameEngine.InputManager.InputManager;
import com.mygdx.game.GameEngine.ScreenManager.Screens.LoadingScreen;
import com.mygdx.game.GameEngine.ScreenManager.Screens.MainScreen;

//a Game to switch between screens
public class MyGdxGame extends Game {

	private Screen currScreen;
	private LoadingScreen loadingScreen;
	private MainScreen mainScreen;
	private AudioSettings preferences;
	public InputManager inputManager;
	public BehaviourManager behaviourManager;
	@Override
	public void create () {
		preferences = new AudioSettings();
    	loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		inputManager = InputManager.getInstance();
		inputManager.setGame(this);
		behaviourManager = new BehaviourManager();
	}

	@Override
	public void setScreen (Screen screen) {
		super.setScreen(screen);
		currScreen = screen;
	}

	public Screen getCurrScreenState(){
		return currScreen;
	}
}
