package fopbot.examples;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;

import java.util.Random;

import static fopbot.World.getWidth;

public class WallMazeExample {

  private static final Random random = new Random();

  public static void main(String[] args) {
    World.setSize(5, 5);

    generateGrid();
    var r = new Robot(0, 0, Direction.UP, 999);

    World.setVisible(true);
    Maze.solveMaze(r);
  }

  private static void generateGrid() {
    recursiveDivide(0, 0, getWidth() - 1, World.getHeight() - 1, random.nextBoolean());
  }

  private static void recursiveDivide(int x1, int y1, int x2, int y2, boolean horizontal) {
    boolean horizontalPossible = y2 - y1 > 0;
    boolean verticalPossible = x2 - x1 > 0;

    if (!horizontalPossible || !verticalPossible) {
      return;
    }

    var x = random.nextInt(x2 - x1) + x1;
    var y = random.nextInt(y2 - y1) + y1;

    if (horizontal) {
      for (int cx = x1; cx <= x2; cx++) {
        if (cx != x) {
          World.putUpWall(cx, y);
        }
      }
      recursiveDivide(x1, y1, x2, y, false);
      recursiveDivide(x1, y + 1, x2, y2, false);
    } else {
      for (int cy = y1; cy <= y2; cy++) {
        if (cy != y) {
          World.putRightWall(x, cy);
        }
      }
      recursiveDivide(x1, y1, x, y2, true);
      recursiveDivide(x + 1, y1, x2, y2, true);
    }
  }
}
