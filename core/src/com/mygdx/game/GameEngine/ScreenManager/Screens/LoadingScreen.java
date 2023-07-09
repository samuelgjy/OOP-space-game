package com.mygdx.game.GameEngine.ScreenManager.Screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GameEngine.ScreenManager.ScreenManager;


public class LoadingScreen extends ScreenManager {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background, title, text;

    public LoadingScreen(Game myGdxGame){
    	super(myGdxGame);
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("background/spacebg.jpg"));
        title = new Texture(Gdx.files.internal("background/title.png"));
        text = new Texture(Gdx.files.internal("background/spacetext.png"));

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new MenuScreen(game));
                }
                return true;
            }
        });
    }
    @Override
    public void render(float delta) {
        game.setScreen(new LoadingScreen(game));
        Gdx.gl.glClearColor(0.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,800,600);
        batch.draw(title,-15,180,1200,200);
        batch.draw(text,250,30,300,60);
        batch.end();
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
