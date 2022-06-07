package fopbot.examples;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;

public class LangtonsAntExample {

  public static void main(String[] args) {
    World.setSize(100, 100);
    World.setVisible(true);

    var ant = new Robot(World.getWidth() / 2, World.getHeight() / 2, Direction.UP, 999);

    while (ant.hasAnyCoins() && ant.isFrontClear()) {
      if (ant.isNextToACoin()) {
        ant.pickCoin();
        ant.pickCoin();

        ant.turnLeft();
        ant.turnLeft();
        ant.turnLeft();
      } else {
        ant.putCoin();
        ant.putCoin();

        ant.turnLeft();

        ant.pickCoin();
        ant.pickCoin();

        ant.putCoin();
        ant.putCoin();
      }
      ant.move();
    }
  }
}
