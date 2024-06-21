package fopbot.anim;

import fopbot.Direction;
import fopbot.definitions.Robot;
import fopbot.impl.AbstractWorld;
import fopbot.impl.CoinStack;
import fopbot.impl.Grid;

import java.awt.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static fopbot.anim.AnimatedWorldFrame.CELL_SIZE;

public class AnimatedWorld extends AbstractWorld {

  private static final double UPDATE_TIMEOUT = 50;

  private final Lock lock = new ReentrantLock();
  private final Condition updateFinished = lock.newCondition();

  private double updateTimeout = 0.0;

  private boolean running = false;

  private int delay = 1000;

  public AnimatedWorld(Grid grid) {
    super(grid);
  }

  public void drawEntities(Drawable d) {
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        for (var e : grid.getEntities(x, y)) {
          if (e instanceof Animatable a) {
            a.draw(d);
          }
        }
      }
    }
  }

  public void drawWalls(Drawable d) {
    d.fill(Color.BLACK);
    d.strokeWeight(8);

    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        var animY = getHeight() - y - 1;
        if (grid.hasUpWall(x, y)) {
          d.line(
            x * CELL_SIZE,
            animY * CELL_SIZE,
            x * CELL_SIZE + CELL_SIZE,
            animY * CELL_SIZE);
        }
        if (grid.hasDownWall(x, y)) {
          d.line(
            x * CELL_SIZE,
            animY * CELL_SIZE + CELL_SIZE,
            x * CELL_SIZE + CELL_SIZE,
            animY * CELL_SIZE + CELL_SIZE);
        }
        if (grid.hasRightWall(x, y)) {
          d.line(
            x * CELL_SIZE + CELL_SIZE,
            animY * CELL_SIZE,
            x * CELL_SIZE + CELL_SIZE,
            animY * CELL_SIZE + CELL_SIZE);
        }
        if (grid.hasLeftWall(x, y)) {
          d.line(
            x * CELL_SIZE,
            animY * CELL_SIZE,
            x * CELL_SIZE,
            animY * CELL_SIZE + CELL_SIZE);
        }
      }
    }
  }

  @Override
  protected CoinStack newCoinStack(int x, int y) {
    var c = new AnimatedCoinStack(this, x, y);
    getEntities(x, y).add(c);
    return c;
  }

  @Override
  public void putBlock(int x, int y) {
    getEntities(x, y).add(new AnimatedBlock(this, x, y));
  }

  @Override
  public Robot newRobot(int x, int y, Direction dir, int numberOfCoins) {
    var r = new AnimatedRobot(x, y, dir, numberOfCoins, this);
    grid.getEntities(x, y).add(r);
    return r;
  }

  @Override
  public void start() {
    running = true;
  }

  @Override
  public void stop() {
    running = false;
  }

  void update(double dt) {
    if (updateTimeout > 0) {
      waitForUpdate(dt);
    } else {
      updateEntities(dt);
    }
  }

  private void updateEntities(double dt) {
    boolean updatesFinished = true;

    for (int x = 0; x < grid.getWidth(); x++) {
      for (int y = 0; y < grid.getHeight(); y++) {
        for (var e : grid.getEntities(x, y)) {
          if ((e instanceof Animatable a) && !a.update(dt)) {
            updatesFinished = false;
          }
        }
      }
    }

    if (updatesFinished) {
      updateTimeout = UPDATE_TIMEOUT;
    }
  }

  private void waitForUpdate(double dt) {
    updateTimeout -= dt;
    if (updateTimeout <= 0) {
      lock.lock();
      try {
        this.updateFinished.signal();
      } finally {
        lock.unlock();
      }
    }
  }

  public void awaitUpdateFinish() {
    if (!running) {
      return;
    }

    lock.lock();
    try {
      updateFinished.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public boolean isRunning() {
    return running;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }
}
