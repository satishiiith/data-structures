import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;

public class SnakeGame {
    int width;
    int height;
    Point [] food;

    int foodEaten;
    LinkedList<Point> snakeStructure;
    Set<Point> snakeStructureSet = new HashSet<>();

    public SnakeGame(int width, int height, Point[] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodEaten = 0;
        Point currentPos = new Point(0,0);
        this.snakeStructure = new LinkedList<Point>();
        this.snakeStructureSet = new HashSet<>();
        snakeStructureSet.add(currentPos);
        snakeStructure.add(currentPos);


    }

    public int  move(Direction direction){
        Point head = snakeStructure.getLast();
        Point tail = snakeStructure.getFirst();
        int rowPos = head.getRow_pos(), colPos = head.getCol_pos();
        if (direction.equals(Direction.U) ){
            rowPos--;
        }
        else if (direction.equals(Direction.D) ){
            rowPos++;
        }
        else if (direction.equals(Direction.R) ){
            colPos++;
        }
        else if (direction.equals(Direction.L) ){
            colPos--;
        }

        // checkboundary
        Point newHead = new Point(rowPos, colPos);


        if(rowPos<0 || rowPos>=height || colPos<0 || colPos>=width)
            return -1;
        if(snakeStructureSet.contains(newHead) && !newHead.equals(tail)){
            return -1;
        }


        //  if eaten food , don't remove tail
        if(foodEaten<food.length && newHead.equals(food[foodEaten])){
            foodEaten++;
        }
        else{
            // tail remove
            this.snakeStructure.poll();
            this.snakeStructureSet.remove(tail);
        }

        snakeStructure.add(newHead);
        snakeStructureSet.add(newHead);
        return  foodEaten;
    }
}
