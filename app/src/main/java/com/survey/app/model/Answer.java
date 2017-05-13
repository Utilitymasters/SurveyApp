package com.survey.app.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class Answer implements Serializable{

    private int questionId;
    private int subQuestionId;
    private boolean isSubQuestionAnswer;
    private String answer;

    private int questionType;


    public Answer(String answer){
        this.answer = answer;
    }
    public Answer(int questionType){
        this.questionType = questionType;
    }
    public Answer(int qId,String answer, int questionType,boolean isSubQuestionAnswer) {
        this.questionId=qId;
        this.answer =answer;
        this.questionType = questionType;
        this.isSubQuestionAnswer=isSubQuestionAnswer;

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSubQuestionId() {
        return subQuestionId;
    }

    public void setSubQuestionId(int subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    public boolean isSubQuestionAnswer() {
        return isSubQuestionAnswer;
    }

    public void setSubQuestionAnswer(boolean subQuestionAnswer) {
        isSubQuestionAnswer = subQuestionAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "questionId=" + questionId +
                ", subQuestionId=" + subQuestionId +
                ", isSubQuestionAnswer=" + isSubQuestionAnswer +
                ", answer='" + answer + '\'' +
                ", questionType=" + questionType +
                '}';
    }
}
