package com.company.weapons;

import com.company.enums.WeaponClasses;
import com.company.dto.ShootDto;
import com.company.spaceships.Spaceship;

public class Railgun implements Weapon{

    private final String name = "Railgun";

    @Override
    public ShootDto shoot(Spaceship from, Spaceship other) {
        System.out.println("Railgun shot!");
        ShootDto shootDto = new ShootDto();
        shootDto.setDamage(300);
        shootDto.setFlyTime(1);
        shootDto.setOrigin(from);
        shootDto.setTarget(other);
        shootDto.setType(WeaponClasses.RAILGUN);
        return shootDto;
    }

    @Override
    public String getName() {
        return name;
    }
}
