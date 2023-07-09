package com.mygdx.game.GameEngine.EntityManager;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameLogic.GameObjects.*;

public class YodaFactory {

    public static Yoda getYoda(int yoda) {
        if (yoda == 73) {
            return new Yoda(MathUtils.random(800,6000), MathUtils.random(0,600),150,70,70);
        }
        if (yoda == 0) {
            return new YodaBirthday(1200, 300,150,420,120);
        }
        if (yoda == 1) {
            return new Yoda73(1200, 300,150,373,120);
        }
        if (yoda == 2) {
            return new YodaZexade(1200, 300,150,420,120);
        }
        if (yoda == 3) {
            return new YodaHexafluoria(1200, 300,150,460,120);
        }
        return null;
    }
}
