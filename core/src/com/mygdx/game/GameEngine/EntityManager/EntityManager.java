package com.mygdx.game.GameEngine.EntityManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.*;
import com.mygdx.game.GameLogic.GameObjects.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {

    public static EntityManager instance;
    private Player player;
    private Healthbar healthbar;
    private SpriteBatch batch;
    private Sprite sprite;
    public Game game;
    private List<Oxygen> OxygenList;
    private List<Asteroid> AsteroidList;
    private List<Satellite> SatelliteList;
    private List<Blackhole> BlackholeList;
    private List<Wormhole> WormholeList;
    private List<WormholeExit> WormholeExitList;
    private List<Yoda> YodaList;
    private Yoda yodaStart;

    public EntityManager() {
        OxygenList = new ArrayList<>();
        AsteroidList = new ArrayList<>();
        SatelliteList = new ArrayList<>();
        BlackholeList = new ArrayList<>();
        WormholeList = new ArrayList<>();
        WormholeExitList = new ArrayList<>();
        YodaList = new ArrayList<>();
        yodaStart = YodaFactory.getYoda(1);
        YodaList.add(yodaStart);
    }

    public static EntityManager getInstance() {
        if (instance == null)
        {
            instance = new EntityManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }
    public List<Oxygen> getOxygenList() {
        return OxygenList;
    }
    public List<Asteroid> getAsteroidList() {
        return AsteroidList;
    }
    public List<Satellite> getSatelliteList() {
        return SatelliteList;
    }
    public List<Blackhole> getBlackholeList() {
        return BlackholeList;
    }
    public List<Wormhole> getWormholeList() {
        return WormholeList;
    }
    public List<WormholeExit> getWormholeExitList() {
        return WormholeExitList;
    }
    public List<Yoda> getYodaList() {
        return YodaList;
    }

    public void create() {
        player = new Player(new Vector2(50,50),10,100,50,100,19,20,21,22);
        healthbar = new Healthbar(50,250,0,250,30, player);
        this.sprite = new Sprite();
    }

    public void loadPlayer(SpriteBatch batch) {
        player.render(batch);
    }

    public void loadHealthbar(SpriteBatch batch){
        healthbar.render(player, batch);
    }

    public void spawnOxygen(){
        for (int i = 0; OxygenList.size()<MathUtils.random(1,4); i++){
            Oxygen oxygen = (Oxygen) CollidableFactory.getEntity("Oxygen");
            OxygenList.add(oxygen);
        }
    }

    public void loadOxygen(SpriteBatch batch) {
        for (int i = 0; i < OxygenList.size(); i++) {
            OxygenList.get(i).render(batch);
        }
        for (Iterator<Oxygen> iter = OxygenList.iterator(); iter.hasNext(); ) {
            Oxygen oxygen = iter.next();

            oxygen.setX(oxygen.getX() - oxygen.getSpeed() * Gdx.graphics.getDeltaTime());
            if (oxygen.getX() + oxygen.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public void spawnAsteroid(){
        for (int i = 0; AsteroidList.size()<MathUtils.random(1,4); i++){
            Asteroid asteroid = (Asteroid) CollidableFactory.getEntity("Asteroid");
            AsteroidList.add(asteroid);
        }
    }
    public void loadAsteroids(SpriteBatch batch){
        for (int i = 0; i<AsteroidList.size(); i++){
            AsteroidList.get(i).render(batch);
        }
        for (Iterator<Asteroid> iter = AsteroidList.iterator(); iter.hasNext(); ) {
            Asteroid asteroid = iter.next();

            asteroid.setX(asteroid.getX() - asteroid.getSpeed() * Gdx.graphics.getDeltaTime());
            if (asteroid.getX() + asteroid.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public void spawnSatellite(){
        for (int i = 0; SatelliteList.size()<MathUtils.random(1,2); i++){
            Satellite satellite = (Satellite) CollidableFactory.getEntity("Satellite");
            SatelliteList.add(satellite);
        }
    }
    public void loadSatellite(SpriteBatch batch) {
        for (int i = 0; i < SatelliteList.size(); i++) {
            SatelliteList.get(i).render(batch);
        }
        for (Iterator<Satellite> iter = SatelliteList.iterator(); iter.hasNext(); ) {
            Satellite satellite = iter.next();

            satellite.setX(satellite.getX() - satellite.getSpeed() * Gdx.graphics.getDeltaTime());
            if (satellite.getX() + satellite.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public void spawnBlackhole(){
        for (int i = 0; BlackholeList.size()<1; i++){
            Blackhole blackhole = (Blackhole) CollidableFactory.getEntity("Blackhole");
            BlackholeList.add(blackhole);
        }
    }
    public void loadBlackhole(SpriteBatch batch) {
        for (int i = 0; i < BlackholeList.size(); i++) {
            BlackholeList.get(i).render(batch);
        }
        for (Iterator<Blackhole> iter = BlackholeList.iterator(); iter.hasNext(); ) {
            Blackhole blackhole = iter.next();

            blackhole.setX(blackhole.getX() - blackhole.getSpeed() * Gdx.graphics.getDeltaTime());
            if (blackhole.getX() + blackhole.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public void spawnWormhole(){
        for (int i = 0; WormholeList.size()<1; i++){
            Wormhole wormhole = (Wormhole) CollidableFactory.getEntity("Wormhole");
            WormholeList.add(wormhole);
        }
    }
    public void loadWormhole(SpriteBatch batch) {
        for (int i = 0; i < WormholeList.size(); i++) {
            WormholeList.get(i).render(batch);
        }
        for (Iterator<Wormhole> iter = WormholeList.iterator(); iter.hasNext(); ) {
            Wormhole wormhole = iter.next();

            wormhole.setX(wormhole.getX() - wormhole.getSpeed() * Gdx.graphics.getDeltaTime());
            if (wormhole.getX() + wormhole.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public void spawnWormholeExit(Wormhole wormhole){
        WormholeExit wormholeExit = new WormholeExit(player.getX(), player.getY(),wormhole.getSpeed(),wormhole.getWidth(),wormhole.getHeight());
        WormholeExitList.add(wormholeExit);
    }
    public void loadWormholeExit(SpriteBatch batch) {
        for (int i = 0; i < WormholeExitList.size(); i++) {
            WormholeExitList.get(i).render(batch);
        }
        for (Iterator<WormholeExit> iter = WormholeExitList.iterator(); iter.hasNext(); ) {
            WormholeExit wormholeExit = iter.next();

            wormholeExit.setX(wormholeExit.getX() - wormholeExit.getSpeed() * Gdx.graphics.getDeltaTime());
            if (wormholeExit.getX() + wormholeExit.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public void spawnYoda(){
        for (int i = 0; YodaList.size()<1; i++){
            Yoda yoda = YodaFactory.getYoda(73);
            YodaList.add(yoda);
        }
    }
    public void spawnYoda(int yoda){
        Yoda yodaFake = YodaFactory.getYoda(yoda);
        YodaList.add(yodaFake);
    }
    public void loadYoda(SpriteBatch batch){
        for (int i = 0; i<YodaList.size(); i++){
            YodaList.get(i).render(batch);
        }
        for (Iterator<Yoda> iter = YodaList.iterator(); iter.hasNext(); ) {
            Yoda yoda = iter.next();

            yoda.setX(yoda.getX() - yoda.getSpeed() * Gdx.graphics.getDeltaTime());
            if (yoda.getX() + yoda.getWidth() < 0) {
                iter.remove();
            }
        }
    }

    public boolean playerState() {
        if (player.getHealth() <= 0) {
            player.setPlayerAlive(false);
        }
        return player.isPlayerAlive();
    }

}
