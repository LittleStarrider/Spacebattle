package com.company.weapons;

import com.company.enums.WeaponClasses;
import com.company.dto.ShootDto;
import com.company.spaceships.Spaceship;

public class Rocket implements Weapon{

    private final String name = "Rocket";

    @Override
    public ShootDto shoot(Spaceship from, Spaceship other) {
        System.out.println("Rockets launched!");
        ShootDto shootDto = new ShootDto();
        shootDto.setDamage(500);
        shootDto.setFlyTime(2);
        shootDto.setOrigin(from);
        shootDto.setTarget(other);
        shootDto.setType(WeaponClasses.ROCKET);
        return shootDto;
    }

    @Override
    public String getName() {
        return name;
    }
}
