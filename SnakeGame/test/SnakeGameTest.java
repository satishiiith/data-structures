import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SnakeGameTest {

    private SnakeGame game;

    @BeforeEach
    public void setUp() {
        // Common setup for all tests if needed
    }

    @Test
    public void testBasicMovement() {
        Point[] food = {};
        game = new SnakeGame(5, 5, food);

        assertEquals(0, game.move(Direction.R), "Move right failed at step 1");
        assertEquals(0, game.move(Direction.D), "Move down failed at step 2");
    }

    @Test
    public void testEatingFood() {
        Point[] food = { new Point(0, 1), new Point(1, 1) };
        game = new SnakeGame(5, 5, food);

        assertEquals(1, game.move(Direction.R), "Eating food failed at step 1");
        assertEquals(2, game.move(Direction.D), "Eating food failed at step 2");
    }

    @Test
    public void testBoundaryCollision() {
        Point[] food = {};
        game = new SnakeGame(3, 3, food);

        game.move(Direction.R);
        game.move(Direction.R);
        assertEquals(-1, game.move(Direction.R), "Boundary collision not detected");
    }

    @Test
    public void testSelfCollision() {
        Point[] food = { new Point(0, 1), new Point(0, 2), new Point(0, 3) };
        game = new SnakeGame(5, 5, food);

        game.move(Direction.R);
        game.move(Direction.R);
        game.move(Direction.R); // Snake is now 4 long
        game.move(Direction.D);
        game.move(Direction.L);
        assertEquals(-1, game.move(Direction.U), "Self-collision not detected");
    }

    @Test
    public void testComplexMovements() {
        Point[] food = { new Point(0, 1), new Point(1, 1), new Point(1, 2), new Point(1, 3) };
        game = new SnakeGame(5, 5, food);

        assertEquals(1, game.move(Direction.R), "Complex movement failed at step 1");
        assertEquals(2, game.move(Direction.D), "Complex movement failed at step 2");
        assertEquals(3, game.move(Direction.R), "Complex movement failed at step 3");
        assertEquals(4, game.move(Direction.R), "Complex movement failed at step 4");
        assertEquals(4, game.move(Direction.U), "Complex movement failed at step 5");
        assertEquals(4, game.move(Direction.L), "Complex movement failed at step 6");
    }
}
