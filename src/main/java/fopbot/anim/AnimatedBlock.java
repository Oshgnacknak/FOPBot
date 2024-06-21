package fopbot.anim;

import fopbot.definitions.World;
import fopbot.anim.resources.Resources;
import fopbot.impl.Block;

import java.awt.*;

import static fopbot.anim.AnimatedWorldFrame.CELL_SIZE;

public class AnimatedBlock extends Block implements Animatable {

  public static final String RESOURCE = "/fopbot/wall.png";
  private final World world;

  public AnimatedBlock(World world, int x, int y) {
    super(x, y);
    this.world = world;
  }

  @Override
  public void draw(Drawable d) {
    d.fill(Color.BLACK);
    var animY = world.getHeight() - y - 1;
    d.image(
      Resources.getImages().get(RESOURCE),
      x * CELL_SIZE,
      animY * CELL_SIZE,
      CELL_SIZE,
      CELL_SIZE);
  }

  @Override
  public boolean update(double dt) {
    return true;
  }
}
