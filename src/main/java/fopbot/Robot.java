package fopbot;

import fopbot.decorate.DecoratingRobot;
import fopbot.global.GlobalScene;

public class Robot extends DecoratingRobot {

  public Robot(int x, int y, Direction dir, int numberOfCoins) {
    super(GlobalScene.INSTANCE.getWorld().newRobot(x, y, dir, numberOfCoins));
  }

  public Robot(int x, int y) {
    super(GlobalScene.INSTANCE.getWorld().newRobot(x, y, Direction.UP, 0));
  }
}
