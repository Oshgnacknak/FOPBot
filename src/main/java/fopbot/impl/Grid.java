package fopbot.impl;

import fopbot.Direction;
import fopbot.Entity;

import java.util.Collection;

public class Grid {

  private final int width;
  private final int height;

  private final Cell[][] grid;

  public Grid(int width, int height) {
    this.width = width;
    this.height = height;

    grid = new Cell[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = new Cell();
      }
    }
  }

  public boolean hasUpWall(int x, int y) {
    return hasWall(x, y, Direction.UP);
  }

  public void setUpWall(int x, int y, boolean wall) {
    setWall(x, y, Direction.UP, wall);
    if (y < height - 1) {
      setWall(x, y + 1, Direction.DOWN, wall);
    }
  }

  public boolean hasDownWall(int x, int y) {
    return hasWall(x, y, Direction.DOWN);
  }

  public void setDownWall(int x, int y, boolean wall) {
    setWall(x, y, Direction.DOWN, wall);
    if (y > 0) {
      setWall(x, y - 1, Direction.UP, wall);
    }
  }

  public boolean hasRightWall(int x, int y) {
    return hasWall(x, y, Direction.RIGHT);
  }

  public void setRightWall(int x, int y, boolean wall) {
    setWall(x, y, Direction.RIGHT, wall);
    if (x < width - 1) {
      setWall(x + 1, y, Direction.LEFT, wall);
    }
  }

  public boolean hasLeftWall(int x, int y) {
    return hasWall(x, y, Direction.LEFT);
  }

  public void setLeftWall(int x, int y, boolean wall) {
    setWall(x, y, Direction.LEFT, wall);
    if (x > 0) {
      setWall(x - 1, y, Direction.RIGHT, wall);
    }
  }

  public boolean hasWall(int x, int y, Direction dir) {
    checkCoords(x, y);
    return getCell(x, y).walls[dir.ordinal()];
  }

  public void setWall(int x, int y, Direction dir, boolean wall) {
    checkCoords(x, y);
    getCell(x, y).walls[dir.ordinal()] = wall;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Collection<Entity> getEntities(int x, int y) {
    checkCoords(x, y);
    return getCell(x, y).entities;
  }

  private void checkCoords(int x, int y) {
    if (x < 0 || x >= width) {
      throw new IllegalArgumentException("X coordinate invalid: " + x);
    }
    if (y < 0 || y >= height) {
      throw new IllegalArgumentException("Y coordinate invalid: " + y);
    }
  }

  private Cell getCell(int x, int y) {
    return grid[x][height - y - 1];
  }
}
