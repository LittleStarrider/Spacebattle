package com.company;

import com.company.dto.CurrentBattleDto;
import com.company.dto.ShootDto;
import com.company.enums.SpaceshipClasses;
import com.company.enums.WeaponClasses;
import com.company.spaceships.Spaceship;

import java.util.List;

public interface Gamelogic {

    Spaceship createSpaceship(SpaceshipClasses spaceshipClass);

    void addWeaponToSpaceship(Spaceship spaceship, WeaponClasses weaponClass) throws Exception;

    CurrentBattleDto mapToCurrentBattleDto(Spaceship currentSpaceship, List<Spaceship> enemySpaceships, List<ShootDto> shootsOnTheWay, int level);

    CurrentBattleDto getCurrentBattleDto();

    List<Spaceship> createEnemyShips(Integer level);

    List<ShootDto> shotsOnTheWayUpdate(List<ShootDto> shotsOnTheWay);

}
