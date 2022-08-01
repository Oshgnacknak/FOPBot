package fopbot.anim;

import fopbot.Direction;
import fopbot.anim.motion.*;
import fopbot.anim.paz.Vector;
import fopbot.anim.resources.Resources;
import fopbot.impl.AbstractRobot;

import static fopbot.anim.AnimatedWorldFrame.*;

public class AnimatedRobot extends AbstractRobot implements Animatable {

  public static final String RESOURCE = "/fopbot/trianglebot.png";

  private static final double UPDATE_EPSILON = 0.05;
  private static final double ANGLE_VEL_SCALAR = 0.003;
  private static final double VEL_SCALAR = 0.001;

  private final AnimatedWorld world;

  private double currentAngle;

  private MotionProfile motion;

  public AnimatedRobot(int x, int y, Direction dir, int numberOfCoins, AnimatedWorld world) {
    super(x, y, dir, numberOfCoins, world);
    this.currentAngle = getAngleOfDir(dir);
    this.world = world;

    setAnimationPosition(x, y);
  }

  private void setAnimationPosition(int x, int y) {
    motion = new Teleport(toAnimationPos(x, y));
  }

  private Vector toAnimationPos(int x, int y) {
    return new Vector(x, world.getHeight() - y - 1)
      .mul(CELL_SIZE)
      .add(CELL_PADDING, CELL_PADDING);
  }

  private void setTarget(int x, int y) {
    var end = toAnimationPos(x, y);
    motion = new TeleportWithDelay(motion.getPos(), end, 1000); // TODO: Delay from user!
  }

  private double getAngleOfDir(Direction dir) {
    return switch (dir) {
      case UP -> Math.PI * 2;
      case LEFT -> 3 * Math.PI / 2;
      case DOWN -> Math.PI;
      default -> Math.PI / 2;
    };
  }

  @Override
  public void turnLeft() {
    world.awaitUpdateFinish();
    super.turnLeft();
  }

  @Override
  public void move() {
    world.awaitUpdateFinish();
    super.move();
  }

  @Override
  public void putCoin() {
    world.awaitUpdateFinish();
    super.putCoin();
  }

  @Override
  public void pickCoin() {
    world.awaitUpdateFinish();
    super.pickCoin();
  }

  @Override
  public Direction getDirection() {
    world.awaitUpdateFinish();
    return super.getDirection();
  }

  @Override
  public int getNumberOfCoins() {
    world.awaitUpdateFinish();
    return super.getNumberOfCoins();
  }

  @Override
  public void turnOff() {
    world.awaitUpdateFinish();
    super.turnOff();
  }

  @Override
  public boolean isTurnedOff() {
    world.awaitUpdateFinish();
    return super.isTurnedOff();
  }

  @Override
  public boolean isNextToACoin() {
    world.awaitUpdateFinish();
    return super.isNextToACoin();
  }

  @Override
  public boolean isNextToARobot() {
    world.awaitUpdateFinish();
    return super.isNextToARobot();
  }

  @Override
  public boolean isFrontClear() {
    world.awaitUpdateFinish();
    return super.isFrontClear();
  }

  @Override
  public void setField(int x, int y) {
    setTarget(x, y);
    super.setField(x, y);
  }

  @Override
  public boolean update(double dt) {
    boolean finished = updateAngle(dt);
    finished &= updatePos(dt);
    return finished;
  }

  private boolean updatePos(double dt) {
    motion.update(dt);
    return motion.reached();
  }

  private boolean updateAngle(double dt) {
    var target = getAngleOfDir(dir);
    while (target > currentAngle) {
      currentAngle += Math.PI * 2;
    }

    currentAngle += (target - currentAngle) * ANGLE_VEL_SCALAR * dt;
    return Math.abs(target - currentAngle) < UPDATE_EPSILON;
  }

  @Override
  public void draw(Drawable d) {
    var w = CELL_SIZE - CELL_PADDING * 2;
    var pos = motion.getPos();
    d.rotated(
      currentAngle,
      pos.x + w / 2,
      pos.y + w / 2,
      () -> d.image(
        Resources.getImages().get(RESOURCE),
        pos.x, pos.y,
        w, w));
  }
}
