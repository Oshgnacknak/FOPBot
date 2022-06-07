package fopbot.global;

import fopbot.Direction;
import fopbot.Robot;

public class SimpleExample {

  public static void main(String[] args) {
    var robot = new Robot(3, 3, Direction.DOWN, 3);

    while (robot.hasAnyCoins()) {
      robot.putCoin();
      robot.move();
    }
  }
}
