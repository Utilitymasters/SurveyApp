package com.survey.app.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class Question implements Serializable {

    private int questionId;
    private int questionType;
    private boolean hasSubQuestion;
    private String question;
    private SubQuestion subQuestion;
    private ArrayList<Answer> answerList = new ArrayList();

    public Question() {

    }

    public Question(int qId, String question, ArrayList<Answer> answerList, boolean hasSubQuestion) {
        this.questionId = qId;
        this.question = question;
        this.answerList = answerList;
        this.hasSubQuestion = hasSubQuestion;

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

    public boolean isHasSubQuestion() {
        return hasSubQuestion;
    }

    public void setHasSubQuestion(boolean hasSubQuestion) {
        this.hasSubQuestion = hasSubQuestion;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public SubQuestion getSubQuestion() {
        return subQuestion;
    }

    public void setSubQuestion(SubQuestion subQuestion) {
        this.subQuestion = subQuestion;
    }


    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionType=" + questionType +
                ", hasSubQuestion=" + hasSubQuestion +
                ", question='" + question + '\'' +
                ", subQuestion=" + subQuestion +
                ", answerList=" + answerList +
                '}';
    }
}
