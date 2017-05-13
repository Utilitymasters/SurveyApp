package com.survey.app.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.survey.app.model.Answer;
import com.survey.app.model.Question;
import com.survey.app.model.SubQuestion;
import com.survey.app.model.SurveyInfo;

import java.util.ArrayList;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class SurveyInteractorImpl implements SurveyInteractor {
    @Override
    public void saveSurvey(final SurveyInfo surveyInfo,final OnSurveyFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(surveyInfo.getName())){
                    listener.onInfoError("Name field can not be blank.");
                    error = true;
                    return;
                }

                if (surveyInfo.getName().length()>10){
                    listener.onInfoError("Name must be less then 10 char.");
                    error = true;
                    return;
                }

                if (TextUtils.isEmpty(surveyInfo.getDob())){
                    listener.onInfoError("Date of birth field is mandatory.");
                    error = true;
                    return;
                }


                if (surveyInfo.getPhone().length() != 10){
                    listener.onInfoError("Phone no. should be in 10 digits only.");
                    error = true;
                    return;
                }

                if (TextUtils.isEmpty(surveyInfo.getAddress())){
                    listener.onInfoError("Address field is mandatory.");
                    error = true;
                    return;
                }
                ArrayList<Question> question = surveyInfo.getQuestionArrayList();

                if (question ==null   || question.size()<1)
                {
                    listener.onInfoError("Add at least one question.");
                    error = true;
                    return;
                }

                for(Question ques : question) {

                    if (TextUtils.isEmpty(ques.getQuestion())) {
                        listener.onInfoError("Question can not be blank.");
                        error = true;
                        return;
                    }

                    ArrayList<Answer> answers = ques.getAnswerList();
                    int count = 0;
                    for (Answer ans : answers) {
                        count++;
                        if (TextUtils.isEmpty(ans.getAnswer())) {
                            listener.onInfoError("Answer # " + count + " can not be blank.");
                            error = true;
                            return;
                        }
                    }

                    SubQuestion subQuestion = ques.getSubQuestion();

                    if (subQuestion != null) {

                        if (TextUtils.isEmpty(subQuestion.getSubQuestion())) {
                            listener.onInfoError("Sub Question can not be blank.");
                            error = true;
                            return;
                        }

                        ArrayList<Answer> subAnswers = subQuestion.getSubQuesAnswerList();
                        count = 0;
                        for (Answer ans : subAnswers) {
                            count++;
                            if (TextUtils.isEmpty(ans.getAnswer())) {
                                listener.onInfoError("SubQuestion: Answer # " + count + " can not be blank.");
                                error = true;
                                return;
                            }
                        }
                    }
                }



                if (!error){


                    //surveyInfo.save();

                    listener.onSuccess();
                }
            }
        }, 1500);


        //save survey info to database.
      //  surveyInfo.save();
    }


}
