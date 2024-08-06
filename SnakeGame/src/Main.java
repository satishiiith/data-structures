public class Main {
    public static void main(String[] args) {

        testComplexMovements1();
        System.out.println("All test cases executed");
    }



    public static void testComplexMovements1() {
        //[[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
        Point[] food = { new Point(1, 2), new Point(0, 1)};
        SnakeGame game = new SnakeGame(3, 2, food);
        int result;

        result = game.move(Direction.R); // Eat (0, 1)
        System.out.println(result);
        assert result == 0 : "Test Case 5 Failed at step 1";

        result = game.move(Direction.D); // Eat (1, 1)
        assert result == 0 : "Test Case 5 Failed at step 2";

        result = game.move(Direction.R); // Eat (1, 2)
        assert result == 1 : "Test Case 5 Failed at step 3";

        result = game.move(Direction.U); // Eat (1, 3)
        assert result == 1 : "Test Case 5 Failed at step 4";

        result = game.move(Direction.L); // Move up
        assert result == 2 : "Test Case 5 Failed at step 5";

        result = game.move(Direction.U); // Move left
        assert result == -1 : "Test Case 5 Failed at step 6";
        System.out.println("Test Case 6 Passed");
    }


}