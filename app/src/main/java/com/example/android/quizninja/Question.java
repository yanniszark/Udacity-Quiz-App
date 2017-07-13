package com.example.android.quizninja;

/**
 * This object represents a quiz question.
 */

public class Question {

    public static final int QUESTION_TYPE_RADIOBUTTON = 0;
    public static final int QUESTION_TYPE_CHECKBOX = 1;
    public static final int QUESTION_TYPE_TEXTENTRY = 2;

    public static final int QUESTION_TYPE_COUNT = 3;

    /* Question Type */
    protected int type;

    /* Question Text */
    protected String text;

    /** Getter method for question type */
    int getType(){
        return type;
    }

    /** Getter method for question text */
    String getText(){
        return text;
    }


}
