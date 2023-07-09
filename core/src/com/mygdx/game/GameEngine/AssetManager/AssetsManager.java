package com.mygdx.game.GameEngine.AssetManager;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {
    private static final AssetManager manager = new AssetManager();
    private Skin skin;
    private Music playingSong;
    // Skin
    public static final String skinPath = "skin/glassy-ui.json";
    public static final String playingSongPath = "audio/maple_island.wav";
    public static final String endSongPath = "audio/end_game_sound.wav";
    public static final String menuSongPath = "audio/menu.wav";
    public static final String oxygenSound = "audio/oxygen.wav";
    public static final String meteorSound = "audio/meteor.wav";
    public static final String wormSound = "audio/wormhole.wav";
    public static final String satelliteSound = "audio/satellite.wav";
    public static final String blackholeSound = "audio/blackhole.wav";
    public static void queueAddSkin(){
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/glassy-ui.atlas");
        manager.load(skinPath, Skin.class, params);
    }

    public static void queueAddMusic(){
        manager.load(playingSongPath, Music.class);
    }
    public static void queueMenuMusic(){manager.load(menuSongPath, Music.class);}
    public static void queueEndMusic(){
        manager.load(endSongPath, Music.class);
    }
    public static void queueOxygenMusic(){
        manager.load(oxygenSound, Music.class);
    }
    public static void queueMeteorMusic(){manager.load(meteorSound, Music.class);
    }
    public static void queueWormholeMusic(){manager.load(wormSound, Music.class);}
    public static void queueSatelliteMusic(){manager.load(satelliteSound, Music.class);}
    public static void queueBlackholeMusic(){manager.load(blackholeSound, Music.class);}
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
    public Skin getSkin() {
        if (skin == null) {
            // Load the skin from the asset manager if it hasn't been loaded yet
            skin = manager.get(skinPath, Skin.class);
        }
        return skin;
    }
    public void setPlayingSong(Music playingSong) {
        this.playingSong = playingSong;
    }

    public Music getPlayingSong() {
        return playingSong;
    }

    public static AssetManager getManager() {
        return manager;
    }
}
