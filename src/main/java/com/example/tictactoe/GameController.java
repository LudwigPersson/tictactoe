package com.example.tictactoe;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.Arrays;

public class GameController {
    private Model model;

    @FXML
    private Button gameButton1;
    @FXML
    private Button gameButton2;
    @FXML
    private Button gameButton3;
    @FXML
    private Button gameButton4;
    @FXML
    private Button gameButton5;
    @FXML
    private Button gameButton6;
    @FXML
    private Button gameButton7;
    @FXML
    private Button gameButton8;
    @FXML
    private Button gameButton9;
    @FXML
    private Label playerXScoreLabel;
    @FXML
    private Label playerOScoreLabel;
    @FXML
    private Label winnerText;
    private Button[] gameButtons;
    private int playerXScore = 0;
    private int playerOScore = 0;

    @FXML
    public void initialize() {
        gameButtons = new Button[]{gameButton1, gameButton2, gameButton3, gameButton4, gameButton5, gameButton6, gameButton7, gameButton8, gameButton9};
        model = new Model(gameButtons);
        Arrays.asList(gameButtons).forEach(button -> {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleButtonAction);
            button.setDisable(false);
            button.setText("");
        });

        model.resetGame();
    }
    @FXML
    public void handleButtonAction(MouseEvent event) {
        Button clickedButton = (Button) event.getSource();
        int buttonIndex = Arrays.asList(gameButtons).indexOf(clickedButton);

        if (model.isMarked(buttonIndex)) {
            return;
        }

        model.markButton(buttonIndex, "X");
        clickedButton.setText("X");

        if (model.checkWinner()) {
            handleGameResult();
        } else {
            model.togglePlayer(model.getCurrentPlayer());

            if (!model.checkWinner()) {
                int computerMove = model.generateRandomMove();
                model.markButton(computerMove, "O");
                gameButtons[computerMove].setText("O");
            }

            if (model.checkWinner()) {
                handleGameResult();
            } else {
                model.togglePlayer(model.getCurrentPlayer());
            }
        }
    }

    @FXML
    private void handleGameResult() {
        String winner = model.getCurrentPlayer();

        if (winner.equals("TIE")) {
            winnerText.setText("It's a tie");
        } else {
            winnerText.setText(winner + " Wins");

            if (winner.equals("X")) {
                playerXScore++;
            } else if (winner.equals("O")) {
                playerOScore++;
            }

            playerXScoreLabel.setText(Integer.toString(playerXScore));
            playerOScoreLabel.setText(Integer.toString(playerOScore));
        }

        disableGameButtons();

        PauseTransition pause = new PauseTransition(Duration.seconds(20));
        pause.setOnFinished(event -> {
            enableGameButtons();
            winnerText.setText("");
            model.resetGame();
        });
        pause.play();
    }

    private void disableGameButtons() {
        for (Button button : gameButtons) {
            button.setDisable(true);
        }
    }
    private void enableGameButtons() {
        Arrays.asList(gameButtons).forEach(button -> button.setDisable(false));
    }

    @FXML
    private void handleResetButtonAction() {
        model.resetGame();


        playerXScore = 0;
        playerOScore = 0;


        playerXScoreLabel.setText("0");
        playerOScoreLabel.setText("0");

        enableGameButtons();
        winnerText.setText("");


        model.togglePlayer("X");
    }
}







