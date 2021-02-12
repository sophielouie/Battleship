package model;

/**
 * Represents a cannon that did not hit anything after being shot.
 */
public class MissCannon implements IGamePiece {
  public MissCannon() {}

  @Override
  public boolean getSunk() {
    throw new UnsupportedOperationException("This method is not supported for MissCannon game piece");
  }

  @Override
  public boolean getPlaced() {
    throw new UnsupportedOperationException("This method is not supported for MissCannon game piece");
  }
}
