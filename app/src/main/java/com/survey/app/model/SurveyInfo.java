package com.survey.app.model;

import android.text.TextUtils;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class SurveyInfo implements Serializable{

    private String name;

    private String dob;

    private String address;

    private String phone;

    private ArrayList<Question> questionArrayList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question> questionList) {
        this.questionArrayList = questionList;
    }

    @Override
    public String toString() {
        return "SurveyInfo{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", questionArrayList=" + questionArrayList +
                '}';
    }
}
