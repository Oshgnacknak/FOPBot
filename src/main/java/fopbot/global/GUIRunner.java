package fopbot.global;

import fopbot.definitions.World;
import fopbot.anim.AnimatedWorldFrame;
import fopbot.anim.resources.Resources;
import fopbot.impl.Grid;

import java.io.IOException;

public class GUIRunner implements GlobalRunner {

  private AnimatedWorldFrame frame;

  @Override
  public World createWorld(Grid grid) {
    try {
      Resources.loadAll();
    } catch (IOException e) {
      e.printStackTrace();
    }

    frame = new AnimatedWorldFrame(grid);

    return frame.getWorld();
  }

  @Override
  public void run() {
    frame.setVisible(true);
    frame.getWorld().start();
    frame.startUpdateThread();
  }
}
