package com.survey.app.presenter;

import com.survey.app.model.SurveyInfo;

/**
 * Created by rohit.anvekar on 5/11/2017.
 */

public class SurveyPresenterImpl implements SurveyPresenter, SurveyInteractor.OnSurveyFinishedListener  {

    private SurveyView surveyView;
    private SurveyInteractor surveyInteractor;

    public SurveyPresenterImpl(SurveyView surveyView) {
        this.surveyView = surveyView;
        this.surveyInteractor = new SurveyInteractorImpl();
    }

    @Override
    public void validateSurveyInfo(SurveyInfo surveyInfo) {

        if (surveyView != null) {
            surveyView.showProgress();
        }

        surveyInteractor.saveSurvey(surveyInfo, this);

    }

    @Override
    public void onDestroy() {

        surveyView = null;

    }

    @Override
    public void onInfoError(String error) {
        if (surveyView != null) {
            surveyView.setInfoError(error);
            surveyView.hideProgress();
        }

    }

    @Override
    public void onSuccess() {
        if (surveyView != null) {
            surveyView.navigateToHome();
        }
    }
}
