package com.company.weapons;

import com.company.dto.ShootDto;
import com.company.spaceships.Spaceship;

public interface Weapon {

    ShootDto shoot(Spaceship from, Spaceship other);

    String getName();
}
