package com.company.dto;

import com.company.spaceships.Spaceship;

import java.util.List;

public class CurrentBattleDto {
    
    private Spaceship currentSpaceship;
    
    private List<Spaceship> enemySpaceships;
    
    private List<ShootDto> shootsOnTheWay;

    private Integer level;

    public Spaceship getCurrentSpaceship() {
        return currentSpaceship;
    }

    public List<Spaceship> getEnemySpaceships() {
        return enemySpaceships;
    }

    public List<ShootDto> getShootsOnTheWay() {
        return shootsOnTheWay;
    }

    public Integer getLevel() {return level;}

    public void setCurrentSpaceship(Spaceship currentSpaceship) {
        this.currentSpaceship = currentSpaceship;
    }

    public void setEnemySpaceships(List<Spaceship> enemySpaceships) {
        this.enemySpaceships = enemySpaceships;
    }

    public void setShootsOnTheWay(List<ShootDto> shootsOnTheWay) {
        this.shootsOnTheWay = shootsOnTheWay;
    }

    public void setLevel(Integer level) {this.level = level;}
}
