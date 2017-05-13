package com.survey.app.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class SubQuestion implements Serializable {

    private int subQuestionId;
    private int questionId;
    private int questionType;
    private String subQuestion;
    private ArrayList<Answer> subQuesAnswerList = new ArrayList();

    public SubQuestion(){

    }

    public SubQuestion(int subQuestionId,int questionId,String subQuestion,ArrayList<Answer> answerArrayList) {

        this.subQuestionId =subQuestionId;
        this.questionId = questionId;
        this.subQuestion = subQuestion;
        this.subQuesAnswerList =answerArrayList;
    }

    public int getSubQuestionId() {
        return subQuestionId;
    }

    public void setSubQuestionId(int subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }


    public String getSubQuestion() {
        return subQuestion;
    }

    public void setSubQuestion(String subQuestion) {
        this.subQuestion = subQuestion;
    }


    public ArrayList<Answer> getSubQuesAnswerList() {
        return subQuesAnswerList;
    }

    public void setSubQuesAnswerList(ArrayList<Answer> subQuesAnswerList) {
        this.subQuesAnswerList = subQuesAnswerList;
    }




}
