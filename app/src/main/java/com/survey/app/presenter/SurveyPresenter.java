package com.survey.app.presenter;

import com.survey.app.model.SurveyInfo;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public interface SurveyPresenter {

    void validateSurveyInfo(SurveyInfo surveyInfo);

    void onDestroy();
}
