package com.mygdx.game.GameEngine.ScreenManager.Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.mygdx.game.GameEngine.AssetManager.AudioSettings;
import com.mygdx.game.GameEngine.ScreenManager.ScreenManager;

import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.menuSongPath;
import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.playingSongPath;


public class PlanetScreen extends ScreenManager {
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background, title;
    private Texture instruction[];
    private int page;
    private final int planets = 3;
    private Skin skin;
    private AudioSettings audioSettings;
    private Music playingSong;

    public PlanetScreen(Game myGdxGame){
        super(myGdxGame);
        page = 1;
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage(new ScreenViewport());
        AssetsManager assetsManager = getAssetManager();
        assetsManager.queueAddSkin();  //new
        assetsManager.getManager().finishLoading(); // new
    }

    @Override
    public void show() {
        AssetsManager.queueMenuMusic();
        AssetsManager.getManager().finishLoading();
        skin = getAssetManager().getSkin();

        AssetsManager.queueAddMusic();
        AssetsManager.getManager().finishLoading();

        background = new Texture(Gdx.files.internal("background/spacebg3.jpg"));
        title = new Texture(Gdx.files.internal("background/planets2.png"));
        instruction = new Texture[planets];
        instruction[0] = new Texture(Gdx.files.internal("background/planet1.png"));
        instruction[1] = new Texture(Gdx.files.internal("background/planet2.png"));
        instruction[2] = new Texture(Gdx.files.internal("background/planet3.png"));
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        batch.begin();

        Table table = new Table();
        table.setFillParent(true);

        stage.addActor(table);

        final TextButton nextButton = new TextButton("Next", skin, "small");
        final TextButton backButton = new TextButton("Back", skin, "small");
        final TextButton homeButton = new TextButton("Home", skin, "small");
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (page >= planets) {
                    page = 1;
                }
                else {
                    page += 1;
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (page <= 1){
                    page = planets;
                }
                else {
                    page -= 1;
                }
            }
        });

        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
        table.row().padTop(450);
        table.add(backButton);
        table.add(homeButton);
        table.add(nextButton);
        batch.end();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(background,0,0,800,600);
        batch.draw(title,150,500,500,70);
        batch.draw(instruction[page-1],160,100,480,400);
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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
