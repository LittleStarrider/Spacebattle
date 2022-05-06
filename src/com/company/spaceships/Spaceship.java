package com.company.spaceships;

import com.company.dto.ShootDto;
import com.company.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Spaceship {

    private boolean playerShip = false;
    private String name;
    private List<Weapon> weapons;
    private Integer weaponSlots;
    private Integer health;
    private Integer healthMax;
    private Integer shield;
    private Integer shieldMax;
    private Integer actionPoints;
    private Integer distanceMultiplier;

    private Random random = new Random();

    public Spaceship(Integer weaponSlots, Integer health, Integer shield, Integer actionPoints, Integer distanceMultiplier, String name) {
        this.weaponSlots = weaponSlots;
        this.health = health;
        this.healthMax = health;
        this.shield = shield;
        this.shieldMax = shield;
        this.actionPoints = actionPoints;
        this.distanceMultiplier = distanceMultiplier;
        this.weapons = new ArrayList<>();
        this.name = name;
    }

    public String takeDamage(ShootDto shootDto) {
        int ran = random.nextInt(distanceMultiplier) + 1;
        if(ran != distanceMultiplier) {
            if (shield > 0) {
                if (shield >= shootDto.getDamage()) {
                    shield -= shootDto.getDamage();
                } else {
                    health -= shootDto.getDamage() - shield;
                    shield = 0;
                }
            } else {
                health -= shootDto.getDamage();
                if(!shootDto.getOrigin().getPlayerShipTrue()) {
                    return shootDto.getType() + " dealt " + shootDto.getDamage() + " damage to your ship";
                } else {
                    return shootDto.getType() + " dealt " + shootDto.getDamage() + " damage to the enemy";
                }
            }
        } else {
            if(!shootDto.getOrigin().getPlayerShipTrue()) {
                return shootDto.getType() + " has missed you!";
            } else {
                return shootDto.getType() + " has missed the enemy!";
            }
        }
        return "";
    }

    public void regenerateHealth(Integer amount){
        this.health += amount;
    }

    public void regenerateShieldFull() {
        this.shield = this.shieldMax;
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public Spaceship withWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        return this;
    }

    //Getters
    public List<Weapon> getWeapons() {
        return weapons;
    }

    public int getWeaponSlots() {
        return weaponSlots;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getShield() {
        return shield;
    }

    public Integer getActionPoints() {
        return actionPoints;
    }

    public Integer getDistanceMultiplier() {
        return distanceMultiplier;
    }

    public Integer getHealthMax() {
        return healthMax;
    }

    public Integer getShieldMax() {
        return shieldMax;
    }

    public String getName() {
        return name;
    }

    public void setPlayerShipTrue() {
        this.playerShip = true;
    }

    public boolean getPlayerShipTrue() {
        return this.playerShip;
    }
}
