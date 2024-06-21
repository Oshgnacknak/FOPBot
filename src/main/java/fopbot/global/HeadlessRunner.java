package fopbot.global;

import fopbot.definitions.World;
import fopbot.headless.HeadlessWorld;
import fopbot.impl.Grid;

public class HeadlessRunner implements GlobalRunner {

  private World world;

  @Override
  public World createWorld(Grid grid) {
    world = new HeadlessWorld(grid);
    return world;
  }

  @Override
  public void run() {
    world.start();
  }
}
