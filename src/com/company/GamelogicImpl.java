package com.company;

import com.company.dto.CurrentBattleDto;
import com.company.dto.ShootDto;
import com.company.enums.SpaceshipClasses;
import com.company.enums.WeaponClasses;
import com.company.spaceships.Artillery;
import com.company.spaceships.Cruiser;
import com.company.spaceships.Dreadnought;
import com.company.spaceships.Spaceship;
import com.company.weapons.Laser;
import com.company.weapons.Railgun;
import com.company.weapons.Rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GamelogicImpl implements Gamelogic{

    private Random random = new Random();
    private CurrentBattleDto currentBattleDto = new CurrentBattleDto();
    private Integer maxSpaceshipAndWeaponTypes = 3;

    public Spaceship createSpaceship(SpaceshipClasses spaceshipClass) {
        switch(spaceshipClass) {
            case DREADNOUGHT -> {
                return new Dreadnought();
            }
            case ARTILLERY -> {
                return new Artillery();
            }
            case CRUISER -> {
                return new Cruiser();
            }
        }
        return null;
    }

    public void addWeaponToSpaceship(Spaceship spaceship, WeaponClasses weaponClass) throws Exception {
        if(spaceship.getWeapons().size() >= spaceship.getWeaponSlots()) {
            throw new Exception("Maximale Anzahl an Waffen schon erreicht");
        } else {
            switch (weaponClass) {
                case LASER -> spaceship.addWeapon(new Laser());
                case ROCKET -> spaceship.addWeapon(new Rocket());
                case RAILGUN -> spaceship.addWeapon(new Railgun());
            }
        }
    }

    public CurrentBattleDto mapToCurrentBattleDto(Spaceship currentSpaceship, List<Spaceship> enemySpaceships, List<ShootDto> shootsOnTheWay, int level) {
        currentBattleDto.setCurrentSpaceship(currentSpaceship);
        currentBattleDto.setEnemySpaceships(enemySpaceships);
        currentBattleDto.setShootsOnTheWay(shootsOnTheWay);
        currentBattleDto.setLevel(level);
        return currentBattleDto;
    }

    public CurrentBattleDto getCurrentBattleDto() {
        return currentBattleDto;
    }

    public List<Spaceship> createEnemyShips(Integer level) {
        int choice;
        List<Spaceship> enemyShips = new ArrayList<>();
        for(int i=0; i<level; i++) {
            choice = random.nextInt(maxSpaceshipAndWeaponTypes)+1;
            Spaceship spaceship;
            switch(choice) {
                case 1 -> spaceship = createSpaceship(SpaceshipClasses.DREADNOUGHT);
                case 2 -> spaceship = createSpaceship(SpaceshipClasses.ARTILLERY);
                case 3 -> spaceship = createSpaceship(SpaceshipClasses.CRUISER);
                default -> spaceship = null;
            }

            for(int j = 0; j< Objects.requireNonNull(spaceship).getWeaponSlots(); j++) {
                choice = random.nextInt(maxSpaceshipAndWeaponTypes)+1;
                switch(choice) {
                    case 1 -> spaceship.addWeapon(new Laser());
                    case 2 -> spaceship.addWeapon(new Rocket());
                    case 3 -> spaceship.addWeapon(new Railgun());
                    default -> spaceship.addWeapon(null);
                }
            }
            enemyShips.add(spaceship);
        }
        return enemyShips;
    }

    public List<ShootDto> shotsOnTheWayUpdate(List<ShootDto> shotsOnTheWay) {
        List<String> hitLog = new ArrayList<>();
        System.out.println("\nCurrent shots on the way--------------");
        for(ShootDto currentShootDto : shotsOnTheWay) {
            if(currentShootDto.getFlyTime() == 0) {
                hitLog.add(currentShootDto.getTarget().takeDamage(currentShootDto));
            } else if(currentShootDto.getFlyTime() > 0){
                if(currentShootDto.getOrigin().getPlayerShipTrue()) {
                    System.out.println(currentShootDto.getType() + " on the way, " + currentShootDto.getFlyTime() + " rounds until it hits --YOURS");
                } else {
                    System.out.println(currentShootDto.getType() + " on the way, " + currentShootDto.getFlyTime() + " rounds until it hits --ENEMY");
                }
            }
            currentShootDto.setFlyTime(currentShootDto.getFlyTime() - 1);
        }
        System.out.println("\nHit Log------------");
        for(String log : hitLog) {
            System.out.println(log);
        }
        System.out.println();
        return shotsOnTheWay;
    }
}
