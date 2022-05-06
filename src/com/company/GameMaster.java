package com.company;

import com.company.menues.BattleRound;
import com.company.menues.SpaceshipCreator;
import com.company.spaceships.Spaceship;

public class GameMaster {

    private Gamelogic gamelogic = new GamelogicImpl();
    private SpaceshipCreator spaceshipCreator = new SpaceshipCreator(gamelogic);
    private BattleRound battleRound = new BattleRound(gamelogic);

    public void startGame() {
        spaceshipCreator.startGame();
        battleRound.startBattleRound(gamelogic.getCurrentBattleDto());
    }
}
