package fopbot.examples;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;

public class BasicExample {
  public static void main() {
    World.setSize(10, 10);

    for (int i = 0; i < 3; i++) {
      var r = new Robot(i, i, Direction.RIGHT, 2);
      while (r.hasAnyCoins()) {
        r.putCoin();
        r.move();
      }
    }
  }
}
