package fopbot.anim.motion;

import fopbot.anim.paz.Vector;

public interface MotionProfile {

  Vector getPos();

  void update(double dt);

  boolean reached();
}
