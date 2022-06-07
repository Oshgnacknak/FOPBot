package fopbot.impl;

import fopbot.Direction;
import fopbot.Entity;
import fopbot.RobotException;
import fopbot.definitions.Robot;
import fopbot.definitions.World;

public abstract class AbstractRobot extends Entity implements Robot {

  private final World world;
  protected boolean on;
  protected int numberOfCoins;
  protected Direction dir;

  public AbstractRobot(int x, int y, Direction dir, int numberOfCoins, World world) {
    super(x, y);
    this.on = true;
    this.dir = dir;
    this.numberOfCoins = numberOfCoins;
    this.world = world;
  }

  @Override
  public void turnLeft() {
    if (!on) {
      return;
    }

    dir = switch (dir) {
      case UP -> Direction.LEFT;
      case RIGHT -> Direction.UP;
      case DOWN -> Direction.RIGHT;
      case LEFT -> Direction.DOWN;
    };
  }

  @Override
  public void move() {
    if (!on) {
      return;
    }

    if (!isFrontClear()) {
      throw new RobotException("Robot crashed!");
    }

    setField(x + getDx(), y + getDy());
  }

  @Override
  public void putCoin() {
    if (!on) {
      return;
    }

    if (numberOfCoins > 0) {
      numberOfCoins--;
      world.putCoins(x, y, 1);
    } else {
      throw new RobotException("Robot went bankrupt!");
    }
  }

  @Override
  public void pickCoin() {
    if (!on) {
      return;
    }

    if (world.pickCoin(getX(), getY())) {
      numberOfCoins++;
    } else {
      throw new RobotException("Robot tried to counterfeit money!");
    }
  }

  @Override
  public Direction getDirection() {
    return dir;
  }

  @Override
  public int getNumberOfCoins() {
    return numberOfCoins;
  }

  @Override
  public void turnOff() {
    this.on = false;
  }

  @Override
  public boolean isTurnedOff() {
    return !on;
  }

  @Override
  public boolean isNextToACoin() {
    return world.hasCoinInField(x, y);
  }

  @Override
  public boolean isNextToARobot() {
    return world.hasAnotherRobotInField(x, y, this);
  }

  @Override
  public void setField(int x, int y) {
    world.getEntities(x, y).remove(this);

    this.x = x;
    this.y = y;

    world.getEntities(x, y).add(this);
  }

  @Override
  public boolean isFrontClear() {
    var dx = getDx();
    var dy = getDy();
    return x + dx >= 0
      && x + dx < world.getWidth()
      && y + dy >= 0
      && y + dy < world.getHeight()
      && !world.fieldHasWallInDirection(x, y, dir)
      && !world.hasBlockInField(x + dx, y + dy);
  }

  private int getDx() {
    return switch (dir) {
      case RIGHT -> +1;
      case LEFT -> -1;
      default -> 0;
    };
  }

  private int getDy() {
    return switch (dir) {
      case UP -> +1;
      case DOWN -> -1;
      default -> 0;
    };
  }
}
