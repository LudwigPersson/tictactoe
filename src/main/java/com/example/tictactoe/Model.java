package com.example.tictactoe;

import java.util.Arrays;
import java.util.Random;

public class Model {
    private final String[] board = new String[9];
    private final boolean[] markedButtons;
    private String currentPlayer;

    public Model() {
        currentPlayer = "X";
        markedButtons = new boolean[9];
    }

    public boolean isMarked(int buttonIndex) {
        return markedButtons[buttonIndex];
    }

    public void markedButton(int position, String marker) {
        if (!markedButtons[position] && ("X".equals(marker) || "O".equals(marker))) {
            board[position] = marker;
            markedButtons[position] = true;
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

    }

    public boolean checkWinner() {
        int[] winningCombinations = {
                0, 1, 2,
                3, 4, 5,
                6, 7, 8,
                0, 3, 6,
                1, 4, 7,
                2, 5, 8,
                0, 4, 8,
                2, 4, 6
        };

        for (int i = 0; i < winningCombinations.length; i += 3) {
            int index1 = winningCombinations[i];
            int index2 = winningCombinations[i + 1];
            int index3 = winningCombinations[i + 2];

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

    public void setWinner(String winner) {
        currentPlayer = winner;
    }

    public boolean hasValidMoves() {
        for (boolean markedButton : markedButtons) {
            if (!markedButton) {
                return true;
            }
        }
        return false;
    }
}

