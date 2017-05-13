package com.survey.app.presenter;

import com.survey.app.model.SurveyInfo;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public interface SurveyInteractor {

    interface OnSurveyFinishedListener {
        void onInfoError(String error);

        void onSuccess();
    }

    void saveSurvey(SurveyInfo surveyInfo, OnSurveyFinishedListener listener);

}