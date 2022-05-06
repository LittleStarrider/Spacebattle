import com.company.Gamelogic;
import com.company.GamelogicImpl;
import com.company.dto.CurrentBattleDto;
import com.company.dto.ShootDto;
import com.company.menues.BattleRound;
import com.company.spaceships.Artillery;
import com.company.spaceships.Cruiser;
import com.company.spaceships.Spaceship;
import com.company.weapons.Laser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BattleRoundTest {

    BattleRound battleRound;
    Gamelogic gamelogic;

    CurrentBattleDto currentBattleDto;
    List<Spaceship> enemyShips;
    ShootDto shootDto;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        gamelogic = mock(GamelogicImpl.class);
        battleRound = new BattleRound(gamelogic);
        currentBattleDto = new CurrentBattleDto();
        currentBattleDto.setLevel(1);
        currentBattleDto.setCurrentSpaceship(new Artillery().withWeapon(new Laser()));
        currentBattleDto.setShootsOnTheWay(new ArrayList<>());

        enemyShips = new ArrayList<>();
        enemyShips.add(new Cruiser());

        shootDto = new ShootDto();
        shootDto.setDamage(99999);
    }

    @Test
    public void gameOverTest() {
        when(gamelogic.createEnemyShips(1)).thenReturn(enemyShips);

        battleRound.getDtoInfo(currentBattleDto);
        battleRound.generateEnemyShips();
        battleRound.enemyShips.get(0).takeDamage(shootDto);
        Assert.assertTrue(battleRound.gameOver());
    }

}
