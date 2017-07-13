package com.example.android.quizninja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    /* ArrayList of Questions */
    ArrayList<Question> questions;

    @BindView(R.id.quiz_questions_linearlayout) LinearLayout quizListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        String[] questionTexts = getResources().getStringArray(R.array.question_text);
        /* Create the arraylist of questions */
        questions = new ArrayList<Question>(Arrays.asList(
                new RadioButtonQuestion(questionTexts[0], getResources().getStringArray(R.array.answers_question_1), 0),
                new TextEntryQuestion(questionTexts[1], Integer.toString(10)),
                new RadioButtonQuestion(questionTexts[2], getResources().getStringArray(R.array.answers_question_3), 3),
                new CheckBoxQuestion(questionTexts[3], getResources().getStringArray(R.array.answers_question_4), new ArrayList<Integer>(Arrays.asList(0, 3))),
                new RadioButtonQuestion(questionTexts[4], getResources().getStringArray(R.array.answers_question_5), 0),
                new RadioButtonQuestion(questionTexts[5], getResources().getStringArray(R.array.answers_question_6), 0),
                new CheckBoxQuestion(questionTexts[6], getResources().getStringArray(R.array.answers_question_7), new ArrayList<Integer>(Arrays.asList(0, 1, 3))),
                new RadioButtonQuestion(questionTexts[7], getResources().getStringArray(R.array.answers_question_8), 3),
                new RadioButtonQuestion(questionTexts[8], getResources().getStringArray(R.array.answers_question_9), 2),
                new TextEntryQuestion(questionTexts[9], Integer.toString(10))
            )
        );

        createQuiz();
    }

    private void createQuiz(){

        Log.v(TAG, "Starting quiz creation");
        for (int i = 0; i < questions.size(); i++){
            /* Inflate a new question layout according to question type */
            LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View questionView;
            switch (questions.get(i).getType()){
                case Question.QUESTION_TYPE_RADIOBUTTON:
                    mInflater.inflate(R.layout.question_radiobutton, quizListView);
                    questionView = quizListView.getChildAt(i);
                    RadioGroup radioGroup = (RadioGroup) questionView.findViewById(R.id.answers_radiogroup);
                    for (int j = 0; j < 4; j++){
                        ((RadioButton) radioGroup.getChildAt(j)).setText(((RadioButtonQuestion) questions.get(i)).getAnswerAt(j));
                    }
                    break;
                case Question.QUESTION_TYPE_CHECKBOX:
                    mInflater.inflate(R.layout.question_checkbox, quizListView);
                    questionView = quizListView.getChildAt(i);
                    LinearLayout linearLayout = (LinearLayout) questionView.findViewById(R.id.answer_checkboxes);
                    for (int j = 0; j < 4; j++){
                        ((CheckBox) linearLayout.getChildAt(j)).setText(( (CheckBoxQuestion) questions.get(i)).getAnswerAt(j));
                    }
                    break;
                case Question.QUESTION_TYPE_TEXTENTRY:
                    mInflater.inflate(R.layout.question_text_entry, quizListView);
                    questionView = quizListView.getChildAt(i);
                    break;
                default:
                    /* This should never happen */
                    return;
            }
            ((TextView)  questionView.findViewById(R.id.question_text)).setText((i+1) + ". " + questions.get(i).getText());

        }

    }

    public void submitQuiz(View view){
        int totalScore = 0;
        /* Check each question's given answer against the correct answer for each question type */
        for (int i = 0; i < questions.size(); i++){
            View questionView;
            switch (questions.get(i).getType()){
                case Question.QUESTION_TYPE_RADIOBUTTON:
                    questionView = quizListView.getChildAt(i);
                    RadioGroup radioGroup = (RadioGroup) questionView.findViewById(R.id.answers_radiogroup);
                    int givenAnswer;
                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.radioButton1:
                            givenAnswer = 0;
                            break;
                        case R.id.radioButton2:
                            givenAnswer = 1;
                            break;
                        case R.id.radioButton3:
                            givenAnswer = 2;
                            break;
                        case R.id.radioButton4:
                            givenAnswer = 3;
                            break;
                        default:
                            givenAnswer= -1;
                    }
                    if (((RadioButtonQuestion) questions.get(i)).checkAnswer(givenAnswer))
                        totalScore++;
                    break;
                case Question.QUESTION_TYPE_CHECKBOX:
                    questionView = quizListView.getChildAt(i);
                    LinearLayout linearLayout = (LinearLayout) questionView.findViewById(R.id.answer_checkboxes);
                    ArrayList<Integer> givenAnswers = new ArrayList<>();
                    for (int j = 0; j < 4; j++){
                        CheckBox checkBox = (CheckBox) linearLayout.getChildAt(j);
                        if (checkBox.isChecked())
                            givenAnswers.add(j);
                    }
                    if (((CheckBoxQuestion) questions.get(i)).isCorrect(givenAnswers))
                        totalScore++;
                    break;
                case Question.QUESTION_TYPE_TEXTENTRY:
                    questionView = quizListView.getChildAt(i);
                    String givenTextAnswer = ((EditText) questionView.findViewById(R.id.answer_text)).getText().toString();
                    if ( ((TextEntryQuestion) questions.get(i)).isCorrect(givenTextAnswer))
                        totalScore++;
                    break;
                default:
                    /* This should never happen */
                    Log.e(TAG, "Something went wrong when scoring ");
                    return;
            }
        }

        /* Make toast showing total score */
        Toast.makeText(this,"Total Score: " + totalScore + " out of " + questions.size(), Toast.LENGTH_LONG).show();
    }

}
