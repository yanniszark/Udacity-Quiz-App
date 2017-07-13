package com.example.android.quizninja;

/**
 * This class represents a radiobutton question.
 */

public class RadioButtonQuestion extends Question {

    /* The list of possible answers */
    private String[] answers;

    /* The number of the correct answer */
    private int correctAnswer;


    public RadioButtonQuestion(String text, String[] answers, int correctAnswer) {
        this.type = Question.QUESTION_TYPE_RADIOBUTTON;
        this.text = text;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Getter method for the question's answers
     */
    public String[] getAnswers() {
        return answers;
    }

    public String getAnswerAt(int i) {
        return answers[i];
    }

    /**
     * Getter method for the number of the correct question
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Checks if the answer given is correct
     */
    public boolean checkAnswer(int givenAnswer) {
        return (correctAnswer == givenAnswer);
    }
}



