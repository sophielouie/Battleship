package model;

/**
 * Represents a cannon that hit a ship after being shot.
 */
public class HitCannon implements IGamePiece {
  public HitCannon() {}

  @Override
  public boolean getSunk() {
    throw new UnsupportedOperationException("This method is not supported for HitCannon game piece");
  }

  @Override
  public boolean getPlaced() {
    throw new UnsupportedOperationException("This method is not supported for HitCannon game piece");
  }
}
