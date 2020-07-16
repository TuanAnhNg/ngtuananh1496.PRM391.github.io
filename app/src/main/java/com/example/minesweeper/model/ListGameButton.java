package com.example.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class ListGameButton {
    List<GameButton> gameButtons;
    List<Bomb> bombs;

    public ListGameButton(List<Bomb> bombs) {
        this.gameButtons = new ArrayList<>();
        this.bombs = bombs;
    }

    public int addGameButton(GameButton gameButton) {
        gameButtons.add(addValueForButton(gameButton));
        return gameButton.getValue();
    }

    public GameButton searchGameButton(int x, int y) {
        for (GameButton gameButton : gameButtons) {
            if (gameButton.getCoordinateX() == x && gameButton.getCoordinateY() == y)
                return gameButton;
        }
        return null;
    }

    private GameButton addValueForButton(GameButton gameButton) {
        int value = 0;
        for (Bomb b : bombs) {
            if (b.getX() == gameButton.getCoordinateX() && b.getY() == gameButton.getCoordinateY())
                gameButton.setValue(-1);
            else {
                if (gameButton.getCoordinateX() == b.getX() && gameButton.getCoordinateY() == (b.getY() + 1)) value++;
                else if (gameButton.getCoordinateX() == (b.getX() + 1) && gameButton.getCoordinateY() == (b.getY() + 1)) value++;
                else if (gameButton.getCoordinateX() == (b.getX() + 1) && gameButton.getCoordinateY() == b.getY()) value++;
                else if (gameButton.getCoordinateX() == (b.getX() + 1) && gameButton.getCoordinateY() == (b.getY() - 1)) value++;
                else if (gameButton.getCoordinateX() == b.getX() && gameButton.getCoordinateY() == (b.getY() - 1)) value++;
                else if (gameButton.getCoordinateX() == (b.getX() - 1) && gameButton.getCoordinateY() == (b.getY() - 1)) value++;
                else if (gameButton.getCoordinateX() == (b.getX() - 1) && gameButton.getCoordinateY() == b.getY()) value++;
                else if (gameButton.getCoordinateX() == (b.getX() - 1) && gameButton.getCoordinateY() == (b.getY() + 1)) value++;
            }
        }
        gameButton.setValue(value);
        return gameButton;
    }
}
