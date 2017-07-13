package com.example.android.quizninja;

import java.util.ArrayList;

/**
 * This class represents a radiobutton question.
 */

public class CheckBoxQuestion extends Question {

    /* The list of possible answers */
    private String[] answers;

    /* Flag to check if the user has given an answer */
    private boolean hasAnswered = false;

    /* The numbers of the correct answers */
    private ArrayList<Integer> correctAnswer;

    /* The answer given by the user */
    private ArrayList<Integer> givenAnswer;

    public CheckBoxQuestion(String text, String[] answers, ArrayList<Integer> correctAnswer) {
        this.type = Question.QUESTION_TYPE_CHECKBOX;
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
    public ArrayList<Integer> getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Checks if the answer given is correct
     */
    public boolean isCorrect(ArrayList<Integer> givenAnswer) {
        return (correctAnswer.equals(givenAnswer));
    }


}
