package fopbot.anim.motion;

import fopbot.anim.paz.Vector;

public class Teleport implements MotionProfile {

  private final Vector pos;

  public Teleport(Vector pos) {
    this.pos = pos;
  }

  @Override
  public Vector getPos() {
    return pos;
  }

  @Override
  public void update(double dt) {
  }

  @Override
  public boolean reached() {
    return true;
  }
}
