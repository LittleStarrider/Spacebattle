package com.company.menues;

import com.company.Gamelogic;
import com.company.enums.SpaceshipClasses;
import com.company.enums.WeaponClasses;
import com.company.spaceships.Artillery;
import com.company.spaceships.Cruiser;
import com.company.spaceships.Dreadnought;
import com.company.spaceships.Spaceship;
import com.company.weapons.Laser;
import com.company.weapons.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

public class SpaceshipCreator {

    private final Scanner scanner = new Scanner(System.in);
    private Gamelogic gamelogic;
    private boolean valid;

    public SpaceshipCreator(Gamelogic gamelogic) {
        this.gamelogic = gamelogic;
    }

    public void startGame() {
        try {
            createSpaceship();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSpaceship() throws Exception {
        printSpaceshipDetails();
        System.out.println("Type in the spaceship you want to use");
        Spaceship selectedSpaceship;

        valid = false;
        do {
            String spaceshipChoice = scanner.nextLine();
            switch (spaceshipChoice.toLowerCase()) {
                case "artillery" -> {
                    selectedSpaceship = gamelogic.createSpaceship(SpaceshipClasses.ARTILLERY);
                    valid = true;
                }
                case "cruiser" -> {
                    selectedSpaceship = gamelogic.createSpaceship(SpaceshipClasses.CRUISER);
                    valid = true;
                }
                case "dreadnought" -> {
                    selectedSpaceship = gamelogic.createSpaceship(SpaceshipClasses.DREADNOUGHT);
                    valid = true;
                }
                default -> {
                    System.out.println("Invalid input, type in the exact name (for example 'Cruiser')");
                    selectedSpaceship = null;
                }
            }
        } while (!valid);

        System.out.println("Now select the weapons you want to put on your Spaceship");
        printWeaponDetails();

        if (selectedSpaceship == null) {
            throw new Exception("Das aktuelle Raumschiff wurde nicht instanziiert");
        } else {
            for(int i = selectedSpaceship.getWeaponSlots(); i>0; i--) {
                valid = false;
                System.out.println("You have " + (i) + " weapon slots left");
                do {
                    String weaponChoice = scanner.nextLine();
                    switch (weaponChoice.toLowerCase()) {
                        case "laser" -> {
                            gamelogic.addWeaponToSpaceship(selectedSpaceship, WeaponClasses.LASER);
                            valid = true;
                        }
                        case "railgun" -> {
                            gamelogic.addWeaponToSpaceship(selectedSpaceship, WeaponClasses.RAILGUN);
                            valid = true;
                        }
                        case "rocket" -> {
                            gamelogic.addWeaponToSpaceship(selectedSpaceship, WeaponClasses.ROCKET);
                            valid = true;
                        }
                        default -> System.out.println("Invalid input, type in the exact name (for example 'Laser')");
                    }
                } while (!valid);
            }
        }

        System.out.println("========================================================================================================\n" +
                "Your spaceship: " + selectedSpaceship.getName() + "\n" +
                "Your weapons : ");
        for(Weapon weapon : selectedSpaceship.getWeapons()) {
            System.out.println(weapon.getName());
        }
        System.out.println("========================================================================================================");

        selectedSpaceship.setPlayerShipTrue();

        gamelogic.mapToCurrentBattleDto(selectedSpaceship, null, new ArrayList<>(), 1);
    }

    private void printSpaceshipDetails() {
        System.out.println("Now its time to create your personal spaceship. Each type has different stats.");

        System.out.println("""
                ********************************************************************************************************
                Artillery - Specialized to shot from far distances, it is difficult to hit for enemy ship. Although the
                grat firepower, it is destroyed rather quickly.
                
                """);
        System.out.println("Health: " + new Artillery().getHealthMax() + "\n" +
                "Shield: " + new Artillery().getShield() + "\n" +
                "Weapon slots: " + new Artillery().getWeaponSlots() + "\n" +
                "Action points: " + new Artillery().getActionPoints() + "\n" +
                "Distance multiplier: " + new Artillery().getDistanceMultiplier() + "\n");

        System.out.println("""
                ********************************************************************************************************
                Cruiser - A well allrounder, decent firepower and health. It shoots from medium distance.
                
                """);
        System.out.println("Health: " + new Cruiser().getHealthMax() + "\n" +
                "Shield: " + new Cruiser().getShield() + "\n" +
                "Weapon slots: " + new Cruiser().getWeaponSlots() + "\n" +
                "Action points: " + new Cruiser().getActionPoints() + "\n" +
                "Distance multiplier: " + new Cruiser().getDistanceMultiplier() + "\n");

        System.out.println("""
                ********************************************************************************************************
                Dreadnought - ...
                
                """);
        System.out.println("Health: " + new Dreadnought().getHealthMax() + "\n" +
                "Shield: " + new Dreadnought().getShield() + "\n" +
                "Weapon slots: " + new Dreadnought().getWeaponSlots() + "\n" +
                "Action points: " + new Dreadnought().getActionPoints() + "\n" +
                "Distance multiplier: " + new Dreadnought().getDistanceMultiplier() + "\n");
        System.out.println("********************************************************************************************************\n");
    }

    private void printWeaponDetails() {
        System.out.println("""
                ********************************************************************************************************
                Laser
                
                Damage: 200
                FlyTime: 0 (Amount of rounds it takes to reach its target)
                ********************************************************************************************************
                """);
        System.out.println("""
                ********************************************************************************************************
                Railgun
                
                Damage: 300
                FlyTime: 1 (Amount of rounds it takes to reach its target)
                ********************************************************************************************************
                """);
        System.out.println("""
                ********************************************************************************************************
                Rocket
                
                Damage: 500
                FlyTime: 2 (Amount of rounds it takes to reach its target)
                ********************************************************************************************************
                """);
    }
}
