package com.mygdx.game.GameEngine.CollisionManager;
import java.util.List;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.mygdx.game.GameEngine.BehaviourManager.BehaviourManager;
import com.mygdx.game.GameEngine.BehaviourManager.PlanetManager;
import com.mygdx.game.GameEngine.EntityManager.EntityManager;
import com.mygdx.game.GameEngine.EntityManager.YodaFactory;
import com.mygdx.game.GameLogic.GameObjects.*;
import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.meteorSound;
import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.oxygenSound;
import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.wormSound;
import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.satelliteSound;
import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.blackholeSound;



public class CollisionManager {

    private static CollisionManager instance;
    private Music playingSong, collideSong, wormholeSong, satelliteSong, blackholeSong;
    public EntityManager entityManager = EntityManager.getInstance();
    public PlanetManager planetManager = PlanetManager.getInstance();

    public CollisionManager() {
        AssetsManager.queueOxygenMusic();
        AssetsManager.queueMeteorMusic();
        AssetsManager.queueWormholeMusic();
        AssetsManager.queueSatelliteMusic();
        AssetsManager.queueBlackholeMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get(oxygenSound);
        collideSong = AssetsManager.getManager().get(meteorSound);
        wormholeSong = AssetsManager.getManager().get(wormSound);
        satelliteSong = AssetsManager.getManager().get(satelliteSound);

    }

    public static CollisionManager getInstance() {
        if (instance == null)
        {
            instance = new CollisionManager();
        }
        return instance;
    }

    public void collideOxygen(Player player) {
        List<Oxygen> OxygenList = entityManager.getOxygenList();
        for (int i = 0; i < OxygenList.size(); i++) {
            Oxygen oxygen = OxygenList.get(i);
            if (player.isCollideBox(oxygen)) {
                player.changeHealthBy(3);
                OxygenList.remove(i);
                System.out.print("+HP\n");
                playingSong.play();
            }
        }
    }

    public void collideAsteroid(Player player) {
        List<Asteroid> AsteroidList = entityManager.getAsteroidList();
        for (int i = 0; i < AsteroidList.size(); i++) {
            Asteroid asteroid = AsteroidList.get(i);
            if (player.isCollideBox(asteroid)) {
                BehaviourManager.handleCollision(player, asteroid);
                player.changeHealthBy(-20);
                AsteroidList.remove(i);
                System.out.print("Player Lost Health :( \n");
                collideSong.play();
            }
        }
    }

    public void collideSatellite(Player player) {
        List<Satellite> SatelliteList = entityManager.getSatelliteList();
        for (int i = 0; i < SatelliteList.size(); i++) {
            Satellite satellite = SatelliteList.get(i);
            if (player.isCollideBox(satellite)) {
                BehaviourManager.handleCollision(player, satellite);
                System.out.print("Obstructed!\n");
                satelliteSong.play();
            }
        }
    }

    public void collideBlackhole(Player player) {
        List<Blackhole> blackholeList = entityManager.getBlackholeList();
        for (int i = 0; i < blackholeList.size(); i++) {
            Blackhole blackhole = blackholeList.get(i);
            if (blackhole.isCollideCircle(player)) {
                blackhole.blackholeGravity(player);
                System.out.print("Event Horizon!\n");

            }
        }
    }

    public void collideWormhole(Player player) {
        List<Oxygen> oxygenList = entityManager.getOxygenList();
        List<Asteroid> asteroidList = entityManager.getAsteroidList();
        List<Satellite> satelliteList = entityManager.getSatelliteList();
        List<Blackhole> blackholeList = entityManager.getBlackholeList();
        List<Wormhole> wormholeList = entityManager.getWormholeList();
        List<WormholeExit> wormholeExitList = entityManager.getWormholeExitList();
        List<Yoda> yodaList = entityManager.getYodaList();
        for (int i = 0; i < wormholeList.size(); i++) {
            Wormhole wormhole = wormholeList.get(i);
            if (wormhole.isCollideBox(player)) {
                wormholeList.remove(i);
                oxygenList.clear();
                asteroidList.clear();
                satelliteList.clear();
                blackholeList.clear();
                wormholeExitList.clear();
                yodaList.clear();

                entityManager.spawnWormholeExit(wormhole);
                planetManager.setPlanetState();
                player.accelerateForward(50);
                entityManager.spawnYoda(planetManager.getPlanetState());
                wormholeSong.play();
                System.out.print("Wormhole!\n");
            }
        }
    }

    public void collideBorder (Player player) {
        if (player.detectBorder(player, 0, 0, 0, 0)) {
            BehaviourManager.handleBorder(player, 0, 0, 0, 0);
        }
    }

    public void collideBorder (Player player, float offset) {
        if (player.detectBorder(player, offset, offset, offset, offset)) {
            BehaviourManager.handleBorder(player, offset, offset, offset, offset);
        }
    }

    public void collideBorder (Player player, float os_left, float os_right, float os_top, float os_bottom) {
        if (player.detectBorder(player, os_left, os_right, os_top, os_bottom)) {
            BehaviourManager.handleBorder(player, os_left, os_right, os_top, os_bottom);
        }
    }

}
