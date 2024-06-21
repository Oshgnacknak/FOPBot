package fopbot.global;

import fopbot.definitions.World;
import fopbot.impl.Grid;

public interface GlobalRunner {

  World createWorld(Grid grid);

  void run();
}
