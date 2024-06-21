package fopbot.anim.motion;

import fopbot.anim.paz.Vector;

public class TriangularMotion implements MotionProfile {

  private final Vector start;

  private final Vector end;

  private final double time;

  private final Vector dir;

  private final double vmax;

  private final double tmax;

  private final double acc;

  private double elapsed = 0;

  public TriangularMotion(Vector start, Vector end, double time) {
    this.start = start;
    this.end = end;
    this.time = time;

    this.dir = end.copy().sub(start);
    this.vmax = 2 * dir.mag() / time;

    this.tmax = time / 2;
    this.acc = vmax / tmax;
  }

  @Override
  public Vector getPos() {
    if (reached()) {
      return end;
    }

    var d =  elapsed < tmax ? dAcc() : dDel();
    return dir.copy().norm().mul(d).add(start);
  }

  private double dAcc() {
    return acc * elapsed * elapsed * 0.5;
  }

  private double dDel() {
    return elapsed * vmax - acc * (0.5 * elapsed * elapsed - elapsed * tmax) - dir.mag();
  }

  @Override
  public void update(double dt) {
    elapsed += dt;
  }

  @Override
  public boolean reached() {
    return elapsed >= time;
  }
}
