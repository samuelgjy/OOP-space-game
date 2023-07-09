package com.mygdx.game.GameEngine.ScreenManager;

import com.badlogic.gdx.Game;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.badlogic.gdx.Screen;

public class ScreenManager implements Screen {
    public Game game;
	private AssetsManager assetsManager = new AssetsManager();

	public ScreenManager(Game game) {
    	this.game = game;

    }
	public AssetsManager getAssetManager(){
		if (assetsManager == null) {
			assetsManager = new AssetsManager();
		}
		return assetsManager;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {	
	}
}
