package controller;

import java.io.IOException;
import model.GameModel;

public interface IController {
  void play(GameModel game) throws IOException;
}
