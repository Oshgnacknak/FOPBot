package fopbot.anim.motion;

import fopbot.anim.paz.Vector;

public class TeleportWithDelay implements MotionProfile {

  private final Vector start;

  private final Vector end;

  private double delay;

  public TeleportWithDelay(Vector start, Vector end, double delay) {
    this.start = start;
    this.end = end;
    this.delay = delay;
  }

  @Override
  public Vector getPos() {
    return reached() ? end : start;
  }

  @Override
  public void update(double dt) {
    delay -= dt;
  }

  @Override
  public boolean reached() {
    return delay <= 0;
  }
}
