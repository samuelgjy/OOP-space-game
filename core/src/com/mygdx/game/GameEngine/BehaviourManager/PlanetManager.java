package com.mygdx.game.GameEngine.BehaviourManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameLogic.GameObjects.Player;

import static com.badlogic.gdx.Gdx.graphics;

public class PlanetManager {
    public static PlanetManager instance;
    private static final int totalPlanets = 3;
    private int planetState;
    private Texture[][] backgrounds;

    // to get timing for the background
    private float backgroundOffSet = 0;
    private float backgroundSpeed;
    private final int WORLD_HEIGHT = 600;
    private final int WORLD_WIDTH = 800;

    public PlanetManager() {
        planetState = 1;
        backgroundSpeed = WORLD_HEIGHT/4;
        backgrounds = new Texture[totalPlanets+1][2];
        backgrounds[0][0] = new Texture("background/spacebg08.jpg");
        backgrounds[0][1] = new Texture("background/spacebg08_flip.jpg");
        backgrounds[1][0] = new Texture("background/spacebg05.jpg");
        backgrounds[1][1] = new Texture("background/spacebg05_flip.png");
        backgrounds[2][0] = new Texture("background/spacebg07.jpeg");
        backgrounds[2][1] = new Texture("background/spacebg07_flip.png");
        backgrounds[3][0] = new Texture("background/spacebg06.jpeg");
        backgrounds[3][1] = new Texture("background/spacebg06_flip.png");
    }

    public static PlanetManager getInstance() {
        if (instance == null)
        {
            instance = new PlanetManager();
        }
        return instance;
    }

    public int getPlanetState() {
        return planetState;
    }

    public void setPlanetState() {
        if (MathUtils.random(70,79) == 73) {
            planetState = 0;
        }
        else if (planetState >= totalPlanets) {
            planetState = 1;
        }
        else {
            planetState += 1;
        }
    }

    public void renderBackground(float deltaTime, SpriteBatch batch) {
        // Move the background image horizontally
        backgroundOffSet += backgroundSpeed * deltaTime;
        batch.draw(backgrounds[planetState][0], 0 - backgroundOffSet, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[planetState][1], WORLD_WIDTH - backgroundOffSet, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(backgrounds[planetState][0], 2 * WORLD_WIDTH - backgroundOffSet, 0, WORLD_WIDTH, WORLD_HEIGHT);

        // Reset the background image position when it moves off the screen
        if (backgroundOffSet >= WORLD_WIDTH * 2) {
            backgroundOffSet = 0;
        }
    }

    public void planetGravity(Player player) {
        float velocity;
        if (planetState == 1) {
            velocity = 173;
        }
        else if (planetState == 2) {
            velocity = 340;
        }
        else if (planetState == 3) {
            velocity = -100;
        }
        else {
            velocity = 0;
        }
        float distance = velocity * graphics.getDeltaTime();
        player.setY(player.getY() - distance);
    }
}
