package com.survey.app.presenter;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public interface SurveyView {


    void showProgress();

    void hideProgress();

    void setInfoError(String error);

    void hideInfoError();

    void navigateToHome();


}
