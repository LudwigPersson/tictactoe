package com.example.tictactoe;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

public class ModelTest {

    @Test //Test för att kontrollera om det finns giltigt drag
    public void testValidMoves() {
        Model model = new Model();

        model.markedButton(0, "X");
        model.markedButton(1, "O");

        assertTrue(model.hasValidMoves());
    }

    @Test //Test för att kontrollera att X kan vinna
    public void testModelCheckWinnerX() {
        Model model = new Model();
        model.markedButton(0, "X");
        model.markedButton(1, "X");
        model.markedButton(2, "X");
        assertTrue(model.checkWinner());
        assertEquals("X", model.getCurrentPlayer());
    }


    @Test //Test så att O kan vinna på andra rader
    public void testModelCheckWinnerO() {
        Model model = new Model();
        model.markedButton(3, "O");
        model.markedButton(4, "O");
        model.markedButton(5, "O");
        assertTrue(model.checkWinner());
        assertEquals("O", model.getCurrentPlayer());
    }


    @Test //Test för att kontrollera om spelet är lika
    public void testTieGame() {
        Model model = new Model();
        model.markedButton(0, "X");
        model.markedButton(1, "O");
        model.markedButton(2, "X");
        model.markedButton(3, "X");
        model.markedButton(4, "O");
        model.markedButton(5, "O");
        model.markedButton(6, "O");
        model.markedButton(7, "X");
        model.markedButton(8, "X");

        assertTrue(model.isTie());
    }
}




