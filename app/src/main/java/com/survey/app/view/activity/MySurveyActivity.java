package com.survey.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.survey.app.R;
import com.survey.app.model.Answer;
import com.survey.app.model.MainCategory;
import com.survey.app.model.Question;
import com.survey.app.model.SubQuestion;
import com.survey.app.model.SurveyInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.survey.app.model.MainCategory.SubCategory;
import com.survey.app.model.MainCategory.SubCategory.SubCategoryItemList;

/**
 * Created by rohit.anvekar on 5/12/2017.
 */
public class MySurveyActivity extends BaseActivity {


    @BindView(R.id.homeImgView)
    ImageView homeImgView;

    @BindView(R.id.title_view)
    TextView titleView;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtPhone)
    TextView txtPhone;

    @BindView(R.id.txtAddress)
    TextView txtAddress;

    @BindView(R.id.linear_listview)
    LinearLayout mLinearListView;


    boolean isFirstViewClick = true;
    boolean isSecondViewClick = true;
    SurveyInfo surveyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        titleView.setText(R.string.my_survey);
        homeImgView.setVisibility(View.VISIBLE);

        ArrayList<Question> questionsList = new ArrayList<>();
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            surveyInfo = (SurveyInfo) bundle.getSerializable("surveyInfo");
            questionsList = surveyInfo.getQuestionArrayList();
        }


        txtName.setText(getString(R.string.name) + "  :  " + surveyInfo.getName());
        txtPhone.setText(getString(R.string.phone) + "  :  " + surveyInfo.getPhone());
        txtAddress.setText(getString(R.string.address) + "  :  " + surveyInfo.getAddress());


        Log.e("questionsList", "" + questionsList.size());

        createQuestionList(questionsList);


    }

    @OnClick(R.id.homeImgView)
    public void navigateToHome(View view) {

        startActivity(new Intent(this,NewSurveyActivity.class));
        finish();

    }


    private void createQuestionList(ArrayList<Question> questionsList) {
        ArrayList<MainCategory> pMainCategoryArrayList = new ArrayList<MainCategory>();


        for (int i = 0; i < questionsList.size(); i++) {
            ArrayList<SubCategory> pSubItemArrayList = new ArrayList<SubCategory>();
            Question ques = questionsList.get(i);


            for (int k = 0; k < ques.getAnswerList().size(); k++) {
                ArrayList<SubCategory.SubCategoryItemList> mSubCategoryItemListArray = new ArrayList<SubCategoryItemList>();

                Answer ans = ques.getAnswerList().get(k);


                if (ques.isHasSubQuestion()) {

                    SubQuestion subQues = ques.getSubQuestion();

                    if (subQues != null) {
                        mSubCategoryItemListArray.add(new SubCategoryItemList(subQues.getSubQuestion(), ""));
                        for (int j = 0; j < subQues.getSubQuesAnswerList().size(); j++) {

                            Answer subAnswer = subQues.getSubQuesAnswerList().get(j);
                            mSubCategoryItemListArray.add(new SubCategory.SubCategoryItemList(subAnswer.getAnswer(), ""));
                        }
                    }

                }

                pSubItemArrayList.add(new SubCategory(ans.getAnswer(), mSubCategoryItemListArray));

            }

            pMainCategoryArrayList.add(new MainCategory(ques.getQuestion(), pSubItemArrayList));

        }

        updateQuestionView(pMainCategoryArrayList);
    }

    private void updateQuestionView(ArrayList<MainCategory> pMainCategoryArrayList) {
        /***
         * adding item into listview
         */
        for (int i = 0; i < pMainCategoryArrayList.size(); i++) {

            LayoutInflater inflater = null;
            inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mLinearView = inflater.inflate(R.layout.row_first, null);

            final TextView mProductName = ButterKnife.findById(mLinearView, R.id.textViewName);
            mProductName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/comicsanslight.ttf"));
            final RelativeLayout mLinearFirstArrow = ButterKnife.findById(mLinearView, R.id.linearFirst);
            final ImageView mImageArrowFirst = ButterKnife.findById(mLinearView, R.id.imageFirstArrow);
            final LinearLayout mLinearScrollSecond = ButterKnife.findById(mLinearView, R.id.linear_scroll);

            if (isFirstViewClick == false) {
                mLinearScrollSecond.setVisibility(View.GONE);
                mImageArrowFirst.setBackgroundResource(R.drawable.ic_add_box_black_24dp);
            } else {
                mLinearScrollSecond.setVisibility(View.VISIBLE);
                mImageArrowFirst.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp);
            }

            mLinearFirstArrow.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if (isFirstViewClick == false) {
                        isFirstViewClick = true;
                        mImageArrowFirst.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp);
                        mLinearScrollSecond.setVisibility(View.VISIBLE);

                    } else {
                        isFirstViewClick = false;
                        mImageArrowFirst.setBackgroundResource(R.drawable.ic_add_box_black_24dp);
                        mLinearScrollSecond.setVisibility(View.GONE);
                    }
                    return false;
                }
            });


            int preIndexMQ =i+1;
            final String name = preIndexMQ+" # "+pMainCategoryArrayList.get(i).getpName();
            mProductName.setText(name);

            /**
             *
             */
            for (int j = 0; j < pMainCategoryArrayList.get(i).getmSubCategoryList().size(); j++) {

                LayoutInflater inflater2 = null;
                inflater2 = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View mLinearView2 = inflater2.inflate(R.layout.row_second, null);

                TextView mSubItemName = ButterKnife.findById(mLinearView2, R.id.textViewTitle);
                mSubItemName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/comicsanslight.ttf"));
                final RelativeLayout mLinearSecondArrow = ButterKnife.findById(mLinearView2, R.id.linearSecond);
                final ImageView mImageArrowSecond = ButterKnife.findById(mLinearView2, R.id.imageSecondArrow);
                final LinearLayout mLinearScrollThird = ButterKnife.findById(mLinearView2, R.id.linear_scroll_third);

                if (isSecondViewClick == false) {
                    mLinearScrollThird.setVisibility(View.GONE);
                    mImageArrowSecond.setBackgroundResource(R.drawable.ic_add_box_black_24dp);
                } else {
                    mLinearScrollThird.setVisibility(View.VISIBLE);
                    mImageArrowSecond.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp);
                }

                mLinearSecondArrow.setOnTouchListener(new OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (isSecondViewClick == false) {
                            isSecondViewClick = true;
                            mImageArrowSecond.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp);
                            mLinearScrollThird.setVisibility(View.VISIBLE);

                        } else {
                            isSecondViewClick = false;
                            mImageArrowSecond.setBackgroundResource(R.drawable.ic_add_box_black_24dp);
                            mLinearScrollThird.setVisibility(View.GONE);
                        }
                        return false;
                    }
                });

                int preIndexQ = j+1;
                String qNo =preIndexQ+" # ";

                final String subCatName =qNo + pMainCategoryArrayList.get(i).getmSubCategoryList().get(j).getpSubCatName();
                mSubItemName.setText(subCatName);
                /**
                 *
                 */
                for (int k = 0; k < pMainCategoryArrayList.get(i).getmSubCategoryList().get(j).getmSubCategoryItemListArray().size(); k++) {

                    LayoutInflater inflater3 = null;
                    inflater3 = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View mLinearView3 = inflater3.inflate(R.layout.row_third, null);

                    TextView mItemName = ButterKnife.findById(mLinearView3, R.id.textViewItemName);
                    mItemName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/comicsanslight.ttf"));
                    int preIndex = i+1;
                    int postIndex = k+1;
                    String subQNo =preIndex+"."+postIndex;

                    final String itemName = subQNo+" # "+ pMainCategoryArrayList.get(i).getmSubCategoryList().get(j).getmSubCategoryItemListArray().get(k).getItemName();

                    mItemName.setText(itemName);


                    mLinearScrollThird.addView(mLinearView3);
                }

                mLinearScrollSecond.addView(mLinearView2);

            }

            mLinearListView.addView(mLinearView);
        }

    }

}
