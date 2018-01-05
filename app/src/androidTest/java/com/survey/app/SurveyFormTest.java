package com.survey.app;

import android.app.Fragment;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.survey.app.view.activity.MySurveyActivity;
import com.survey.app.view.activity.NewSurveyActivity;
import com.survey.app.view.activity.SurveyListActivity;
import com.survey.app.view.fragment.SurveyInfoFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by webonise on 3/1/18.
 */

public class SurveyFormTest extends
        ActivityInstrumentationTestCase2<NewSurveyActivity> {

    private Solo solo;

    private long waitTime = 1000l;

    public SurveyFormTest() {
        super(NewSurveyActivity.class);
    }




    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(getInstrumentation(),getActivity());
    }


    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }


    public void test_view() throws Exception{
        solo.unlockScreen();
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Name field can not be blank.");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtName),"RohitAnvekar");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Name must be less then 10 char.");
        solo.clearEditText((android.widget.EditText) solo.getView(R.id.edtName));
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtName),"Rohit A");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Date of birth field is mandatory.");
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtDob),"23/10/1987");
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtPhone),"12345671189");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Phone no. should be in 10 digits only.");
        solo.clearEditText((android.widget.EditText) solo.getView(R.id.edtPhone));
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtPhone),"1234567890");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Address field is mandatory.");
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtAddress),"Rahatani");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Add at least one question.");
        solo.clickOnView((android.widget.TextView) solo.getView(R.id.txtAddNewQuestion));
        solo.waitForText("");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Question can not be blank.");
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtQuestion),"Who is Narendra Modi.");
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForText("Answer # 1 can not be blank.");
        solo.enterText((android.widget.EditText) solo.getView(R.id.edtAnswer1),"India's Prime Minister");
        solo.waitForFragmentById(solo.getCurrentActivity().getFragmentManager().findFragmentById(R.id.survey_info_container).getId());
        solo.clickOnView(solo.getView(R.id.btnSave));
        solo.waitForActivity(SurveyListActivity.class, 4000);
    }
}
