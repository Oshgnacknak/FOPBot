package fopbot;

import fopbot.global.GlobalScene;

public class World {

  private World() {}

  public static void setSize(int width, int height) {
    GlobalScene.INSTANCE.createWorld(width, height);
  }

  public static void setVisible(boolean visible) {
    if (visible) {
      GlobalScene.INSTANCE.run();
    }
  }

  public static int getWidth() {
    return GlobalScene.INSTANCE.getWorld().getWidth();
  }

  public static int getHeight() {
    return GlobalScene.INSTANCE.getWorld().getHeight();
  }

  public static void putBlock(int x, int y) {
    GlobalScene.INSTANCE.getWorld().putBlock(x, y);
  }

  public static void putUpWall(int x, int y) {
    GlobalScene.INSTANCE.getWorld().getGrid().setUpWall(x, y, true);
  }

  public static void putDownWall(int x, int y) {
    GlobalScene.INSTANCE.getWorld().getGrid().setDownWall(x, y, true);
  }

  public static void putRightWall(int x, int y) {
    GlobalScene.INSTANCE.getWorld().getGrid().setRightWall(x, y, true);
  }

  public static void putLeftWall(int x, int y) {
    GlobalScene.INSTANCE.getWorld().getGrid().setLeftWall(x, y, true);
  }

  public static void putCoins(int x, int y, int numberOfCoins) {
    GlobalScene.INSTANCE.getWorld().putCoins(x, y, numberOfCoins);
  }

  /**
   * @deprecated Use the equivalent <code>putDownWall(x, y)</code>
   */
  @Deprecated
  public static void placeVerticalWall(int x, int y) {
    putDownWall(x, y);
  }

  /**
   * @deprecated Use the equivalent <code>putRightWall(x, y)</code>
   */
  @Deprecated
  public static void placeHorizontalWall(int x, int y) {
    putRightWall(x, y);
  }

  public static void setDelay(int delay) {
    GlobalScene.INSTANCE.setDelay(delay);
  }
}
