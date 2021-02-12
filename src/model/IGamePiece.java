package model;

public interface IGamePiece {

  /**
   * Returns if the ship has sunk or not
   *
   * @return boolean
   */
  boolean getSunk();

  /**
   * Returns if the ship has been placed or not
   *
   * @return boolean
   */
  boolean getPlaced();

}
