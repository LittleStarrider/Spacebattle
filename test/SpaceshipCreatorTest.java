import com.company.Gamelogic;
import com.company.GamelogicImpl;
import com.company.enums.SpaceshipClasses;
import com.company.enums.WeaponClasses;
import com.company.menues.SpaceshipCreator;
import com.company.spaceships.Cruiser;
import com.company.spaceships.Spaceship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;
import java.util.Scanner;

@RunWith(MockitoJUnitRunner.class)
public class SpaceshipCreatorTest {

    Gamelogic gamelogic;
    Scanner scanner;
    SpaceshipCreator spaceshipCreator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        gamelogic = new GamelogicImpl();
        spaceshipCreator = new SpaceshipCreator(gamelogic);
        scanner = mock(Scanner.class);
    }

    @Test
    public void createSpaceshipTest() throws Exception {
        when(scanner.nextLine()).thenReturn("Cruiser", "Laser", "Rocket");

        spaceshipCreator.createSpaceship();
        boolean valid = true;

        if(!Objects.equals(gamelogic.getCurrentBattleDto().getCurrentSpaceship().getName(), "Cruiser")) {
            valid = false;
        }
        if(gamelogic.getCurrentBattleDto().getCurrentSpaceship().getWeapons().size() != 2) {
            valid = false;
        }

        Assert.assertTrue(valid);
    }

}
