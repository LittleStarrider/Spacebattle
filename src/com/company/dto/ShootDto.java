package com.company.dto;

import com.company.enums.WeaponClasses;
import com.company.spaceships.Spaceship;

public class ShootDto {
    private int damage;

    private int flyTime;

    private WeaponClasses type;

    public void setType(WeaponClasses type) {
        this.type = type;
    }

    public WeaponClasses getType() {
        return type;
    }

    private Spaceship target;

    private Spaceship origin;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setFlyTime(int flyTime) {
        this.flyTime = flyTime;
    }

    public void setTarget(Spaceship target) {
        this.target = target;
    }

    public void setOrigin(Spaceship origin) {
        this.origin = origin;
    }

    public int getDamage() {
        return damage;
    }

    public int getFlyTime() {
        return flyTime;
    }

    public Spaceship getTarget() {
        return target;
    }

    public Spaceship getOrigin() {
        return origin;
    }
}
