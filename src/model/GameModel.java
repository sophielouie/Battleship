package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

  //0: personal screen (ship view)  1: attack screen
  List<Grid> p1;
  //0: personal screen  1: attack screen
  List<Grid> p2;
  List<Ship> p1Ships;
  List<Ship> p2Ships;
  // total number of ships that have yet to be placed
  // after placing all ships, these variable should remain 0 for the duration of the game
  private int p1ShipCount;
  private int p2ShipCount;
  //total successful hits by that player
  private int p1Hit;
  private int p2Hit;

  /**
   * Constructor that creates and adds two grids per player, also instantiates the ship lists to the
   * starting amount/type of ships
   */
  public GameModel() {
    //create and add two screens/grids to each player
    this.p1 = new ArrayList<Grid>();
    p1.add(new Grid());
    p1.add(new Grid());
    this.p2 = new ArrayList<Grid>();
    p2.add(new Grid());
    p2.add(new Grid());

    //instantiate the list of ships for each player
    //  X3 3 cell ship, X1 4 cell ship, X1 5 cell ship, X1 2 cell ship
    this.p1Ships = new ArrayList<Ship>();
    p1Ships.add(new Ship(3));
    p1Ships.add(new Ship(3));
    p1Ships.add(new Ship(3));
    p1Ships.add(new Ship(4));
    p1Ships.add(new Ship(5));
    p1Ships.add(new Ship(2));

    this.p2Ships = new ArrayList<Ship>();
    p2Ships.add(new Ship(3));
    p2Ships.add(new Ship(3));
    p2Ships.add(new Ship(3));
    p2Ships.add(new Ship(4));
    p2Ships.add(new Ship(5));
    p2Ships.add(new Ship(2));

    this.p1ShipCount = 6;
    this.p2ShipCount = 6;
    this.p1Hit = 0;
    this.p2Hit = 0;
  }

  /**
   * Checks if the placement of the ship is valid and that the user still has to place that specific
   * ship before replacing the empty cells with ship game pieces
   *
   * @param firstX
   * @param firstY
   * @param secX
   * @param secY
   * @param size
   * @param player
   */
  public void placeShip(int firstX, int firstY, int secX, int secY, int size, int player) {
    if (firstX < 0 || firstX > 9 || firstY < 0 || firstY > 9 || secX < 0 || secX > 9 || secY < 0 || secY > 9) {
      throw new IllegalArgumentException("Invalid coordinates: given x or y position do not exist on the grid");
    }
    //check if the first and second x,y placement is valid with the given ship size
    //gotta subtract 1 from size so that it matches counting from 0
    if (((firstX == secX) && (Math.abs(firstY - secY) != (size - 1)))
        || ((firstY == secY) && (Math.abs(firstX - secX) != (size - 1)))) {
      throw new IllegalArgumentException(
          "The given start and end coordinates do not match the size of the ship");
    }
    //if neither are equal to each other then user is trying to attempt a non vertical or horizontal placement
    else if ((firstX != secX) && (firstY != secY)) {
      throw new IllegalArgumentException("Invalid placement formation: cannot place ship diagonally");
    }
    else {
      if (player == 0) {
        //checks if all the ships have been placed
        if (this.p1ShipCount == 0) {
          throw new IllegalArgumentException("All the ships have been placed");
        }
        //checks if the ship of that size is placed or not yet
        boolean includes = false;
        for (Ship s : this.p1Ships) {
          //sees if there is a ship in the list with the given size
          if (s.size == size && s.getPlaced() == false) {
            includes = true;
          }
        }
        if (includes) {
          //first subtract from the number of the ships to still place
          this.p1ShipCount--;
          //next place the same ship in all the cells that it covers
          boolean first = false;
          for (int i = 0; i < this.p1Ships.size(); i++) {
            if (this.p1Ships.get(i).size == size && this.p1Ships.get(i).getPlaced() == false
                && first == false) {
              //change the ship status to 'placed'
              this.p1Ships.get(i).place();
              first = true;
              //call the grid, replace those cells with a ship IGamePiece
              p1.get(0).placeShip(firstX, firstY, secX, secY, this.p1Ships.get(i));
            }
          }
        } else {
          throw new IllegalArgumentException("There are no more ships of that size to be placed");
        }
      } else if (player == 1) {
        //checks if all the ships have been placed
        if (this.p2ShipCount == 0) {
          throw new IllegalArgumentException("All the ships have been placed");
        }
        //checks if the ship of that size is placed or not yet
        boolean includes = false;
        for (Ship s : this.p2Ships) {
          //sees if there is a ship in the list with the given size
          if (s.size == size && s.getSunk() == false) {
            includes = true;
          }
        }
        if (includes) {
          //first subtract from the number of the ships to still place
          this.p2ShipCount--;
          //next place the same ship in all the cells that it covers
          boolean first = false;
          for (int i = 0; i < this.p2Ships.size(); i++) {
            if (this.p2Ships.get(i).size == size && this.p2Ships.get(i).getPlaced() == false
                && first == false) {
              //change the ship status to 'placed'
              this.p2Ships.get(i).place();
              first = true;
              //call the grid, replace those cells with a ship IGamePiece
              p2.get(0).placeShip(firstX, firstY, secX, secY, this.p2Ships.get(i));
            }
          }
        }
        else {
          throw new IllegalArgumentException(
              "There are no more ships of that size to be placed");
        }
      } else {
        throw new IllegalArgumentException("Not a valid player name");
      }
    }
  }

  /**
   * Returns true or false depending on whether the shot was successful in hitting a ship, changes
   * the ship's status to hit
   *
   * @param x
   * @param y
   * @param player
   * @return String
   */
  public String shoot(int x, int y, int player) {
    //checks to see if given x,y is off the grid
    if (x < 0 || x > 9 || y < 0 || y > 9) {
      throw new IllegalArgumentException("Invalid coordinates: given x or y position do not exist on the grid");
    }


    if (player == 0) {

      //cannot shoot before all your ships have been placed
      if (this.p1ShipCount != 0) {
        throw new IllegalStateException("Cannot fire a cannon before all your ships are placed");
      }

      IGamePiece hitPiece = p2.get(0).hit(x, y);
      //if the game piece at given x,y is a ship
      if (hitPiece instanceof Ship) {

        this.p1Hit++;
        //change the attack screen to reflect hit cannon
        p1.get(1).get(x).set(y, new HitCannon());

        //change p2's personal screen to show the attack
        p2.get(0).get(x).set(y, new HitCannon());

        //lets user know if that have sunk a ship as a result of their hit
        if (hitPiece.getSunk()) {
          return "Hit, P1 has sunk a ship!\n";
        }
        return "P1, Hit!\n";
      }
      //the cannon missed
      else {
        //change p1 attack screen
        p1.get(1).get(x).set(y, new MissCannon());
        //change p2 personal screen
        p2.get(0).get(x).set(y, new MissCannon());
        return "P1, Miss!\n";
      }
    } else if (player == 1) {
      IGamePiece hitPiece = p1.get(0).hit(x, y);
      //if the game piece at given x,y is a ship
      if (hitPiece instanceof Ship) {

        this.p2Hit++;
        //change the attack screen to reflect hit cannon
        p2.get(1).get(x).set(y, new HitCannon());

        //change p1's personal screen to show the attack
        p1.get(0).get(x).set(y, new HitCannon());

        //lets user know if that have sunk a ship as a result of their hit
        if (hitPiece.getSunk()) {
          return "Hit, P2 has sunk a ship!\n";
        }
        return "P2, Hit!\n";
      }
      //the cannon missed
      else {
        //change p2 attack screen
        p2.get(1).get(x).set(y, new MissCannon());
        //change p1 personal screen
        p1.get(0).get(x).set(y, new MissCannon());
        return "P2, Miss!\n";
      }
    } else {
      throw new IllegalArgumentException("Not a valid player");
    }
  }

  /**
   * Changes the isGameOver to reflect the game state The game ends when a player reaches 20 hits,
   * that means they have sunk all the opposing pirateships
   *
   * @return boolean
   */
  public boolean isGameOver() {
    if (this.p1Hit == 20) {
      return true;
    }
    if (this.p2Hit == 20) {
      return true;
    }
    return false;
  }

  /**
   * Returns a string corresponding to the winner of the game, only called after isGameOver() = True
   *
   * @return string
   */
  public String winner() {
    if (this.p1Hit == 20) {
      return "Player 1 wins!";
    }
    else {
      return "Player 2 wins!";
    }
  }

  /**
   * Returns the list of player 1 screens/grids.
   *
   * @return
   */
  public List<Grid> getP1() {
    return this.p1;
  }

  /**
   * Returns the list of player 2 screens/grids.
   *
   * @return
   */
  public List<Grid> getP2() {
    return this.p2;
  }

  /**
   * Returns P1 number of successful hits.
   *
   * @return integer
   */
  public int getP1Hit() { return this.p1Hit;}

  /**
   * Returns P2 number of successful hits.
   *
   * @return integer
   */
  public int getP2Hit() { return this.p2Hit;}

  /**
   * Returns P1 ships.
   *
   * @return list of ships
   */
  public List<Ship> getP1Ships() { return this.p1Ships;}

  /**
   * Returns P2 ships.
   *
   * @return list of ships
   */
  public List<Ship> getP2Ships() { return this.p2Ships;}

}
