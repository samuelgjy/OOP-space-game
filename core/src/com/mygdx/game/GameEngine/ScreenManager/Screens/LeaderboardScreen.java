package com.mygdx.game.GameEngine.ScreenManager.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameEngine.ScreenManager.ScreenManager;

import java.util.*;
import java.util.stream.Collectors;

public class LeaderboardScreen extends ScreenManager {
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background, title;
    private Skin skin;
    public LeaderboardScreen(Game myGdxGame){
        super(myGdxGame);
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage(new ScreenViewport());
        AssetsManager assetsManager = getAssetManager();
        assetsManager.queueAddSkin();  //new
        assetsManager.getManager().finishLoading(); // new
    }
    @Override
    public void show() {
        Table table = new Table();
        skin = getAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background/spacebg2.jpg"));
        title = new Texture(Gdx.files.internal("background/leaderboard.png"));

        FileHandle file = Gdx.files.internal("data/leaderboard.csv");
        String text = file.readString();
//        String[] lines = text.split("\\r?\\n");
//        text = text.substring(text.indexOf('\n') + 1);

        // create a map of scores with their corresponding timestamps
        // read the lines from the CSV file
        String[] lines = file.readString().split("\\r?\\n");

        // create a map of scores with their corresponding timestamps
        Map<Integer, String> scores = new HashMap<>();
        for (int i = 1; i < lines.length; i++) { // skip the first row
            String[] parts = lines[i].split(",");
            int score = Integer.parseInt(parts[1]);
            String timestamp = parts[0];
            scores.put(score, timestamp);
        }

        // sort the scores in descending order and get the top 3
        List<Integer> topScores = new ArrayList<>(scores.keySet());
        Collections.sort(topScores, Collections.reverseOrder());
        topScores = topScores.subList(0, Math.min(topScores.size(), 3));

        // iterate through the top 3 scores and add the rows
        for (int i = 0; i < topScores.size(); i++) {
            int score = topScores.get(i);
            String timestamp = scores.get(score);
            table.row();
            // ranking
            Label ranking = new Label(String.valueOf(i + 1), skin);
            float rankingWidth = Gdx.graphics.getWidth() * 0.1f;
            table.add(ranking).width(rankingWidth).height(50).pad(10);
            // timestamp
            Label timestampLabel = new Label(timestamp, skin);
            float timestampWidth = Gdx.graphics.getWidth() * 0.5f;
            table.add(timestampLabel).width(timestampWidth).height(50).pad(10);
            // score
            Label scoreLabel = new Label(String.valueOf(score), skin);
            float scoreWidth = Gdx.graphics.getWidth() * 0.2f;
            table.add(scoreLabel).width(scoreWidth).height(50).pad(10);
        }
        table.setSize(500, 400);
//        table.add(actor).expandX().expandY().fill().pad(10);
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        stage.addActor(table);

        final TextButton backButton = new TextButton("Back", skin, "small");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
        table.row().center();
        table.add(backButton).width(100).height(50).pad(10);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,800,600);
        batch.draw(title,60,500,700,100);
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
