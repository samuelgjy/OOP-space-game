package com.mygdx.game.GameEngine.ScreenManager.Screens;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetManager.AudioSettings;
import com.mygdx.game.GameEngine.ScreenManager.ScreenManager;
import com.badlogic.gdx.audio.Music;


public class MenuScreen extends ScreenManager {
    private Stage stage;
    private Skin skin;
    private Texture background, buzz;
    private SpriteBatch batch;
    private Music playingSong;
    private AudioSettings audioSettings;
    private Slider audioSlider;

    public MenuScreen(Game myGdxGame){
        super(myGdxGame);
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        audioSettings = new AudioSettings();
        AssetsManager assetsManager = getAssetManager();
        assetsManager.queueAddSkin();  //new
        assetsManager.getManager().finishLoading(); // new
    }
    
    @Override
    public void show() {
        AssetsManager.queueMenuMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get("audio/menu.wav");
        playingSong.setVolume(audioSettings.getAudioVolume());
        playingSong.play();
        background = new Texture(Gdx.files.internal("background/spacebg.jpg"));
        buzz = new Texture(Gdx.files.internal("background/buzz.png"));
        //create stage and set as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = getAssetManager().getSkin();
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // create buttons
        TextButton newGame = new TextButton("New Game", skin, "small");
        TextButton planets = new TextButton("Planets", skin, "small");
        TextButton instructions = new TextButton("Instructions", skin, "small");
        TextButton leaderboard = new TextButton("Leaderboard", skin, "small");
        TextButton preferences = new TextButton("Preferences", skin, "small");
        TextButton exit = new TextButton("Exit", skin, "small");

        // add button to table
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 5, 0);
        table.add(planets).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(instructions).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(leaderboard).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(preferences).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(exit).fillX().uniformX();

        //button listener
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainScreen(game));
                playingSong.stop();
            }
        });
        planets.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlanetScreen(game));
            }
        });
        instructions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new InstructionScreen(game));
            }
        });
        leaderboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LeaderboardScreen(game));
            }
        });
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PreferencesScreen(game));
            }
        });
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        //clear screen and prep for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0.4f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background,0,0,800,600);
        batch.draw(buzz,610,220,140,200);
        batch.end();

        //ask stage to do action and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change stage viewport when screen size is changed
        stage.getViewport().update(width, height, true);
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
        AssetsManager.getManager().dispose();
        stage.dispose();
    }
}
