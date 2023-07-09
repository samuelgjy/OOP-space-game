package com.mygdx.game.GameEngine.ScreenManager.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameEngine.BehaviourManager.PlanetManager;
import com.mygdx.game.GameEngine.EntityManager.EntityManager;
import com.mygdx.game.GameEngine.BehaviourManager.BehaviourManager;
import com.mygdx.game.GameEngine.CollisionManager.CollisionManager;
import com.mygdx.game.GameEngine.InputManager.InputManager;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine.ScreenManager.ScreenManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.playingSongPath;

public class MainScreen extends ScreenManager {
    public InputManager inputManager = InputManager.getInstance();
    public BehaviourManager behaviourManager = BehaviourManager.getInstance();
    private SpriteBatch batch;
    private Music playingSong;
    private float score;
    private int scoreInt;
    private String currentScore;
    BitmapFont scoreText = new BitmapFont();

    public EntityManager entityManager = EntityManager.getInstance();
    public CollisionManager collisionManager = CollisionManager.getInstance();
    public PlanetManager planetManager = PlanetManager.getInstance();

    public MainScreen(Game myGdxGame) {
        super(myGdxGame);
        batch = new SpriteBatch();
        entityManager.create();
        AssetsManager assetsManager = getAssetManager();
    }

    @Override
    public void show() {

        AssetsManager.queueAddMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get(playingSongPath);
        playingSong.play();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ESCAPE) {
                    game.setScreen(new MenuScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0.2f, 0, 0);
        batch.begin();
        planetManager.renderBackground(deltaTime, batch);
        planetManager.planetGravity(entityManager.getPlayer());

        entityManager.spawnYoda();
        entityManager.spawnOxygen();
        entityManager.spawnAsteroid();
        entityManager.spawnSatellite();
        entityManager.spawnBlackhole();
        entityManager.spawnWormhole();

        entityManager.loadYoda(batch);
        entityManager.loadOxygen(batch);
        entityManager.loadAsteroids(batch);
        entityManager.loadSatellite(batch);
        entityManager.loadBlackhole(batch);
        entityManager.loadWormhole(batch);
        entityManager.loadWormholeExit(batch);

        entityManager.playerState();
        entityManager.loadPlayer(batch);
        entityManager.loadHealthbar(batch);
        inputManager.handleInput(entityManager.getPlayer());
        behaviourManager.checkTime(deltaTime, entityManager.getPlayer());

        collisionManager.collideBorder(entityManager.getPlayer(), 25, 200, 30, 30);
        collisionManager.collideOxygen(entityManager.getPlayer());
        collisionManager.collideAsteroid(entityManager.getPlayer());
        collisionManager.collideSatellite(entityManager.getPlayer());
        collisionManager.collideBlackhole(entityManager.getPlayer());
        collisionManager.collideWormhole(entityManager.getPlayer());
        collisionManager.collideWormhole(entityManager.getPlayer());

        if (!entityManager.playerState()) {
            game.setScreen(new EndScreen(game, scoreInt));
        }

        score += deltaTime*10;
        scoreInt = (int)score;
        currentScore = "score: " + scoreInt;

        scoreText.setColor(255,255,255,255);
        scoreText.draw(batch, currentScore, 620,580);
        scoreText.getData().setScale(2,2);

        batch.end();
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
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
        super.hide();
        playingSong.stop();

    }

    @Override
    public void dispose() {
    }
}
