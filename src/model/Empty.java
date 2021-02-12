package model;

public class Empty implements IGamePiece {
  public Empty() {}

  @Override
  public boolean getSunk() {
    throw new UnsupportedOperationException("This method is not supported for Empty game piece");
  }

  @Override
  public boolean getPlaced() {
    throw new UnsupportedOperationException("This method is not supported for Empty game piece");
  }
}
