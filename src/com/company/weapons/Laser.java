package com.company.weapons;

import com.company.enums.WeaponClasses;
import com.company.dto.ShootDto;
import com.company.spaceships.Spaceship;

public class Laser implements Weapon {

    private final String name = "Laser";

    @Override
    public ShootDto shoot(Spaceship from, Spaceship other) {
        System.out.println("Laser activated!");
        ShootDto shootDto = new ShootDto();
        shootDto.setDamage(200);
        shootDto.setFlyTime(0);
        shootDto.setOrigin(from);
        shootDto.setTarget(other);
        shootDto.setType(WeaponClasses.LASER);
        return shootDto;
    }

    @Override
    public String getName() {
        return name;
    }
}
