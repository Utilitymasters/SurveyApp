package com.survey.app.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.survey.app.R;
import com.survey.app.view.fragment.SurveyInfoFragment;
import com.survey.app.view.fragment.SurveyQuestionFragment;
import com.survey.app.model.Question;
import com.survey.app.presenter.SurveyPresenter;
import com.survey.app.presenter.SurveyPresenterImpl;
import com.survey.app.presenter.SurveyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rohit.anvekar on 5/9/2017.
 */

public class NewSurveyActivity extends BaseActivity implements SurveyView {


    @BindView(R.id.homeImgView)
    ImageView homeImgView;
    @BindView(R.id.title_view)
    TextView titleView;
    @BindView(R.id.mainScrollView)
    ScrollView mainScrollView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.survey_info_container)
    FrameLayout surveyInfoContainer;
    @BindView(R.id.survey_questions_container)
    FrameLayout surveyQuestionContainer;
    @BindView(R.id.survey_questions_container1)
    FrameLayout surveyQuestionContainer1;
    @BindView(R.id.survey_questions_container2)
    FrameLayout surveyQuestionContainer2;
    @BindView(R.id.survey_questions_container3)
    FrameLayout surveyQuestionContainer3;
    @BindView(R.id.survey_questions_container4)
    FrameLayout surveyQuestionContainer4;
    @BindView(R.id.txtAddNewQuestion)
    TextView txtAddQuestion;
    @BindView(R.id.txtInfoError)
    TextView txtInfoError;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.dynamicContainer)
    RelativeLayout relativeLayout;


    Bundle savedInstanceState;

    SurveyQuestionFragment surveyQuestionFragment = null;
    SurveyInfoFragment surveyInfoFragment = null;
    SurveyPresenter surveyPresenter;
    ArrayList<SurveyQuestionFragment> fragmentsList =new ArrayList<>();
    ArrayList<Question> questionsList =new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);
        this.savedInstanceState = savedInstanceState;
        ButterKnife.bind(this);

        titleView.setText(R.string.new_survey);
        homeImgView.setVisibility(View.GONE);

        // setSupportActionBar(toolbar);
        // toolbar.setLogo(R.drawable.ic_home_black_24dp);

        addSurveyInfoFragment();


        surveyPresenter = new SurveyPresenterImpl(this);
    }


    private void addSurveyInfoFragment() {

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (surveyInfoContainer != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            surveyInfoFragment = new SurveyInfoFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            surveyInfoFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction()
                    .add(surveyInfoContainer.getId(), surveyInfoFragment).commit();
        }
    }


    @OnClick(R.id.txtAddNewQuestion)
    public void addNewQuestion(View view) {

      if(!checkIfFragmentContainerAvailable()){
          Toast.makeText(this,getString(R.string.add_question_restriction_error),Toast.LENGTH_LONG).show();
      }
        //addQuestionFragment();
        surveyInfoFragment.updateSurveyInfo();

    }


    @OnClick(R.id.btnSave)
    public void save(View view) {
        //   navigateToHome();

        surveyInfoFragment.updateSurveyInfo();
       /* if(surveyQuestionFragment!=null) {
            surveyQuestionFragment.getUpdatedQuestionDetail();
            surveyInfoFragment.getSurveyInfo().setQuestion(surveyQuestionFragment.getQuestion());
            Log.i("getSurveyInfo:", "" + surveyInfoFragment.getSurveyInfo().toString());
            Log.i("getQuestion:", "" + surveyQuestionFragment.getQuestion().toString());

        }*/
        //reset if any previous data is their.
        questionsList.clear();

        for(int i=0;i<fragmentsList.size();i++){
            SurveyQuestionFragment surveyQuestionFragment = fragmentsList.get(i);
            questionsList.add(surveyQuestionFragment.getUpdatedQuestionDetail());

        }
        surveyInfoFragment.getSurveyInfo().setQuestionArrayList(questionsList);


        surveyPresenter.validateSurveyInfo(surveyInfoFragment.getSurveyInfo());
       /* if(surveyQuestionFragment == null) {
            txtInfoError.setText(getString(R.string.add_question_error));
            txtInfoError.setVisibility(View.VISIBLE);
        }*/

    }

    @OnClick(R.id.btnCancel)
    public void cancel(View view) {
        finish();
    }

    @OnClick(R.id.homeImgView)
    public void home(View view) {

    }


    private void addQuestionFragment() {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (surveyQuestionContainer != null && surveyQuestionFragment == null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            surveyQuestionFragment = new SurveyQuestionFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            surveyQuestionFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(surveyQuestionContainer.getId(), surveyQuestionFragment).commit();
        } else {
            showMessage(getString(R.string.add_question_error));
        }

    }



    private boolean checkIfFragmentContainerAvailable( ) {
        boolean isContainerAvailable = true;
        if(surveyQuestionContainer.getChildCount() == 0){
            addQuestionFragment(surveyQuestionContainer);
            return isContainerAvailable;
        }
        if(surveyQuestionContainer1.getChildCount() == 0){
            addQuestionFragment(surveyQuestionContainer1);
            return isContainerAvailable;
        }

        if(surveyQuestionContainer2.getChildCount() == 0){
            addQuestionFragment(surveyQuestionContainer2);
            return isContainerAvailable;
        }
        if(surveyQuestionContainer3.getChildCount() == 0){
            addQuestionFragment(surveyQuestionContainer3);
            return isContainerAvailable;
        }
        if(surveyQuestionContainer4.getChildCount() == 0){
            addQuestionFragment(surveyQuestionContainer4);
            return isContainerAvailable;
        }
        isContainerAvailable =false;

        return isContainerAvailable;
    }

        private void addQuestionFragment(FrameLayout surveyQuestionContainer ) {

            // Create a new Fragment to be placed in the activity layout
           SurveyQuestionFragment surveyQuestionFragment = new SurveyQuestionFragment();

            fragmentsList.add(surveyQuestionFragment);
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            surveyQuestionFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(surveyQuestionContainer.getId(), surveyQuestionFragment).addToBackStack("Fragment"+surveyQuestionContainer.getId()).commit();

            mainScrollView.post(new Runnable() {
                @Override
                public void run() {
                    // This method works but animates the scrolling
                    // which looks weird on first load
                    // scroll_view.fullScroll(View.FOCUS_DOWN);

                    // This method works even better because there are no animations.
                    mainScrollView.scrollTo(0, mainScrollView.getBottom());
                }
            });

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setInfoError(String error) {

        txtInfoError.setVisibility(View.VISIBLE);
        txtInfoError.setText(error);
        mainScrollView.scrollTo(0,0);
    }

    @Override
    public void hideInfoError() {
        txtInfoError.setVisibility(View.GONE);
    }

    @Override
    public void navigateToHome() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("surveyInfo",surveyInfoFragment.getSurveyInfo());
        Intent intent =new Intent(this, MySurveyActivity.class);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();

        if(fm.getBackStackEntryCount() > 0){
            fm.popBackStack();
            fragmentsList.remove(fragmentsList.size()-1);
            showMessage("Question removed from the list.");

        } else{
            finish();
        }
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
