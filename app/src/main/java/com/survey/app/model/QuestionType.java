package com.survey.app.model;

import java.util.ArrayList;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class QuestionType {
    public static final int SINGLE_CHOICE = 0;
    public static final int MULTIPLE_CHOICE = 1;
    public static final int MULTILINE_CHOICE = 2;

    ArrayList<Answer> answerTypeList =null;

    public QuestionType() {
    }

   public ArrayList<Answer> getAnswerTypeList(int questionType) {

        answerTypeList = new ArrayList<>();

        if (questionType == SINGLE_CHOICE || questionType == MULTILINE_CHOICE) {

            answerTypeList.add(new Answer(questionType == SINGLE_CHOICE ? SINGLE_CHOICE : MULTILINE_CHOICE));

        } else if(questionType == MULTIPLE_CHOICE) {

            answerTypeList.add(new Answer(MULTIPLE_CHOICE));
            answerTypeList.add(new Answer(MULTIPLE_CHOICE));
            answerTypeList.add(new Answer(MULTIPLE_CHOICE));
        }

        return answerTypeList;
    }


}
