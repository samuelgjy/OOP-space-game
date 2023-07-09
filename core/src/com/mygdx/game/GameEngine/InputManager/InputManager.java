package com.mygdx.game.GameEngine.InputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.GameEngine.ScreenManager.Screens.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.GameLogic.GameObjects.Healthbar;
import com.mygdx.game.GameLogic.GameObjects.Player;

public class InputManager {

    private MyGdxGame game;

    public void setGame(MyGdxGame game) {
        this.game = game;
    }

    private static InputManager instance;

    public InputManager() {
        instance = this;
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void handleInput(Player obj1) {
        if (game.getCurrScreenState() instanceof MainScreen) {
            if (Gdx.input.isKeyPressed(obj1.getUpKey())) {
                // up
                game.behaviourManager.moveUp(obj1);
                // function in behaviour mgmt;
            } if (Gdx.input.isKeyPressed(obj1.getDownKey())) {
                // down
                game.behaviourManager.moveDown(obj1);
            } if (Gdx.input.isKeyPressed(obj1.getLeftKey())) {
                // left
                game.behaviourManager.moveLeft(obj1);
            } if (Gdx.input.isKeyPressed(obj1.getRightKey())) {
                // right
                game.behaviourManager.moveRight(obj1);
            }
        }
    }
}
