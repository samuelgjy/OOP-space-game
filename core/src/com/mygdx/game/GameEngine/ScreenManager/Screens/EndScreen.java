package com.mygdx.game.GameEngine.ScreenManager.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
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
import com.mygdx.game.GameEngine.ScreenManager.ScreenManager;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class EndScreen extends ScreenManager {
    //test
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Music playingSong;
    private Texture background,text;
    private Skin skin;
    private int finalScore;
    private String scoreString = "Final Score: " + finalScore;
    BitmapFont scoreText = new BitmapFont();

    public EndScreen(Game myGdxGame, int finalScore){
        super(myGdxGame);
        this.finalScore = finalScore;
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage(new ScreenViewport());
        AssetsManager assetsManager = getAssetManager();
    }
    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("background/end_screen_bg.jpg"));
        text = new Texture(Gdx.files.internal("background/you_lost.png"));
        stage.clear();

        AssetsManager.queueEndMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get("audio/end_game_sound.wav");
        playingSong.play();

        Gdx.input.setInputProcessor(stage);

        batch.begin();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);
        AssetsManager.queueAddSkin();
        AssetsManager.getManager().finishLoading();
        skin = getAssetManager().getSkin();


        final TextButton scoreLabel = new TextButton("Final Score: " + finalScore, skin, "small");
        table.add(scoreLabel).padTop(20).center();
        scoreLabel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LeaderboardScreen(game));
                Gdx.app.log("leaderboard button", "leaderboard is pressed");
            }
        });

        final TextButton newGame = new TextButton("Replay", skin, "small");
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainScreen(game));
                Gdx.app.log("return button", "return is pressed");

            }
        });

        final TextButton menuButton = new TextButton("Home", skin, "small");
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
                Gdx.app.log("home button", "home is pressed");
            }
        });

        table.row();
        table.add(newGame);
        table.add(menuButton);
        table.add(scoreLabel).padLeft(30);


        batch.end();

        try {
            writeCsv(finalScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,800,600);
        batch.draw(text,160,350,500,100);
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void writeCsv(int finalScore) throws IOException {
        // create a file handle for the CSV file
        FileHandle file = new FileHandle("assets/data/leaderboard.csv");
        file.file().setWritable(true);

        // create a FileWriter object to write to the file
        FileWriter writer = new FileWriter(file.file(), true);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String rowContent = timeStamp + "," + finalScore + "\n";
        // write some data to the file
        writer.append(rowContent);
        System.out.println(rowContent);
        // flush and close the writer
        writer.flush();
        writer.close();

        System.out.println("CSV file successfully written.");
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