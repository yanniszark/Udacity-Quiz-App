package com.example.android.quizninja;

/**
 *
 */

public class TextEntryQuestion extends Question {

    /* The number of the correct answer */
    private String correctAnswer;

    public TextEntryQuestion(String text, String correctAnswer){
        this.type = Question.QUESTION_TYPE_TEXTENTRY;
        this.text = text;
        this.correctAnswer = correctAnswer;
    }


    /** Getter method for the number of the correct question */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /** Checks if the answer given is correct */
    public boolean isCorrect(String givenAnswer){
        return (correctAnswer.equalsIgnoreCase(givenAnswer));
    }
}
