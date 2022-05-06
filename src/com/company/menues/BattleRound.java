package com.company.menues;

import com.company.Gamelogic;
import com.company.dto.CurrentBattleDto;
import com.company.dto.ShootDto;
import com.company.spaceships.Spaceship;
import com.company.weapons.Weapon;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleRound {

    private Random random = new Random();
    private Gamelogic gamelogic;
    private CurrentBattleDto currentBattleDto;
    public List<Spaceship> enemyShips;
    private List<ShootDto> shotsOnTheWay;
    private Spaceship currentSpaceship;
    private Scanner scanner = new Scanner(System.in);

    public BattleRound(Gamelogic gamelogic) {
        this.gamelogic = gamelogic;
    }

    public void startBattleRound(CurrentBattleDto currentBattleDto) {
        getDtoInfo(currentBattleDto);
        generateEnemyShips();
        while (!gameOver()) {
            yourTurn();
            if(gameOver()){
                break;
            }
            enemyTurn();

            shotsOnTheWay = gamelogic.shotsOnTheWayUpdate(shotsOnTheWay);
            System.out.println("*************************************************************");
            System.out.println("\nYour ship has " + currentBattleDto.getCurrentSpaceship().getHealth() + " hp, " + currentBattleDto.getCurrentSpaceship().getShield() + " shield");
            System.out.println("*************************************************************\n");
        }
    }

    public void getDtoInfo(CurrentBattleDto currentBattleDto) {
        this.currentBattleDto = currentBattleDto;
        this.shotsOnTheWay = currentBattleDto.getShootsOnTheWay();
        this.currentSpaceship = currentBattleDto.getCurrentSpaceship();
    }

    public void generateEnemyShips() {
        enemyShips = gamelogic.createEnemyShips(currentBattleDto.getLevel());
    }

    public void printEnemyShips() {
        int iteratorShips = 1;
        for(Spaceship enemyShip : enemyShips) {
            if(enemyShip.getHealth() <= 0) {
                System.out.println(iteratorShips + ":   " + enemyShip.getName() + ": Destroyed");
            } else {
                System.out.println(iteratorShips + ":   " + enemyShip.getName() + ": " + enemyShip.getHealth() + "hp, " + enemyShip.getShield() + "shield");
            }
            for(Weapon weapon : enemyShip.getWeapons()) {
                System.out.println("-" + weapon.getName());
            }
            iteratorShips++;
        }
    }

    private void printWeapons() {
        int iteratorWeapons = 1;
        for(Weapon weapon : currentBattleDto.getCurrentSpaceship().getWeapons()) {
            System.out.println(iteratorWeapons + ": " + weapon.getName());
            iteratorWeapons++;
        }
    }

    public void yourTurn() {
        int choice;
        boolean valid;
        printEnemyShips();
        System.out.println("Which spaceship do you want to shoot? Input number");

        Spaceship spaceshipTarget;
        do {
            valid = false;
            choice = scanner.nextInt();
            if(checkInputRange(1, enemyShips.size(), choice)) {
                valid = true;
            } else {
                System.out.println("Input out of range");
            }
        } while(!valid);
        spaceshipTarget = enemyShips.get(choice-1);

        System.out.println("With which weapon do you want to shoot? Input number");
        printWeapons();

        Weapon weaponChoice;
        do {
            valid = false;
            choice = scanner.nextInt();
            if(choice>=1 && choice<= currentBattleDto.getCurrentSpaceship().getWeapons().size()) {
                valid = true;
            } else {
                System.out.println("Input out of range");
            }
        } while(!valid);
        weaponChoice = currentBattleDto.getCurrentSpaceship().getWeapons().get(choice-1);

        shotsOnTheWay.add(weaponChoice.shoot(currentSpaceship, spaceshipTarget));
    }

    public void enemyTurn() {
        int choice;
        boolean valid;
        do {
            valid = false;
            choice = random.nextInt(enemyShips.size());
            if(enemyShips.get(choice).getHealth() > 0) {
                valid = true;
            }
        } while (!valid);
        Spaceship enemyShip = enemyShips.get(choice);
        choice = random.nextInt(enemyShip.getWeaponSlots());
        shotsOnTheWay.add(enemyShip.getWeapons().get(choice).shoot(enemyShip, currentSpaceship));
    }

    private boolean checkInputRange(int min, int max, int choice) {
        return (choice>=min && choice<= max);
    }

    public boolean gameOver() {
        int counter = 0;
        for(Spaceship enemyShip : enemyShips) {
            if(enemyShip.getHealth() <= 0) {
                counter += 1;
            }
        }
        if(counter == enemyShips.size()) {
            System.out.println("You won!");
            return true;
        } else if (currentSpaceship.getHealth() <= 0) {
            System.out.println("You have been destroyed");
            return true;
        }
        return false;
    }
}
