package com.example.tictactoe;

import javafx.scene.control.Button;
import java.util.Arrays;
import java.util.Random;

public class Model {
    public String[] board = new String[9];
    private final Button[] gameButtons;
    boolean[] markedButtons;
    private String currentPlayer;


    public Model(Button[] gameButtons) {
        this.gameButtons = gameButtons;
        currentPlayer = "X";
        markedButtons = new boolean[9];
    }

    public boolean isMarked(int buttonIndex) {
        return markedButtons[buttonIndex];
    }

    public void markButton(int position, String marker) {
        if (!markedButtons[position]) {
            Button button = gameButtons[position];
            button.setText(marker);
            button.setDisable(true);
            markedButtons[position] = true;
            board[position] = marker;
        }
    }
    public boolean isTie() {
        for (boolean markedButton : markedButtons) {
            if (!markedButton) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        Arrays.fill(board, null);
        Arrays.fill(markedButtons, false);

        for (Button button : gameButtons) {
            button.setText("");
            button.setDisable(false);
        }
    }
    public boolean checkWinner() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winningCombinations) {
            int index1 = combination[0];
            int index2 = combination[1];
            int index3 = combination[2];

            String marker1 = board[index1];
            String marker2 = board[index2];
            String marker3 = board[index3];

            if (marker1 != null && marker1.equals(marker2) && marker1.equals(marker3)) {
                setWinner(marker1);
                return true;
            }
        }

        if (isTie()) {
            setWinner("TIE");
            return true;
        }

        return false;
    }

    public int generateRandomMove() {
        Random random = new Random();
        int randomMove;
        do {
            randomMove = random.nextInt(9);
        } while (isMarked(randomMove));
        return randomMove;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void togglePlayer(String winner) {
        if (winner.equals("X")) {
            currentPlayer = "O";
        } else if (winner.equals("O")) {
            currentPlayer = "X";
        }
    }
    private void setWinner(String marker) {
        currentPlayer = marker;
    }
}
