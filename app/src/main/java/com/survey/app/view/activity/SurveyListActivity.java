package com.survey.app.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.survey.app.R;
import com.survey.app.model.Answer;
import com.survey.app.model.Question;
import com.survey.app.model.SurveyInfo;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by webonise on 5/1/18.
 */

public class SurveyListActivity extends BaseActivity {

    @BindView(R.id.questionRecycleView)
    RecyclerView questionRecycleView;

    private SurveyInfo surveyInfo;
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_list);
        ArrayList<Question> questions = new ArrayList<>();
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            surveyInfo = (SurveyInfo) bundle.getSerializable("surveyInfo");
            questions = surveyInfo.getQuestionArrayList();
        }

        questionAdapter = new QuestionAdapter(questions);
        questionRecycleView.setAdapter(questionAdapter);
    }


    class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

        private ArrayList<Question> questionsList = new ArrayList<>();

        public QuestionAdapter(ArrayList<Question> questions) {
            questionsList = questions;
        }


        @Override
        public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View questionListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
            return new QuestionViewHolder(questionListView);
        }

        @Override
        public void onBindViewHolder(QuestionViewHolder holder, int position) {
            Question question = questionsList.get(position);
            holder.txtQuestion.setText(question.getQuestion());
            ArrayList<Answer> answerArrayList = questionsList.get(position).getAnswerList();

            if (answerArrayList.get(0) != null) {
                holder.txtAnswer1.setText(answerArrayList.get(0).getAnswer());
                holder.txtAnswer1.setVisibility(View.VISIBLE);
            }
            if (answerArrayList.get(1) != null) {
                holder.txtAnswer2.setText(answerArrayList.get(1).getAnswer());
                holder.txtAnswer1.setVisibility(View.VISIBLE);
            }
            if (answerArrayList.get(2) != null) {
                holder.txtAnswer3.setText(answerArrayList.get(2).getAnswer());
                holder.txtAnswer1.setVisibility(View.VISIBLE);
            }


        }


        @Override
        public int getItemCount() {
            return questionsList.size();
        }
    }


    public class QuestionViewHolder extends RecyclerView.ViewHolder {


        private TextView txtQuestion, txtAnswer1, txtAnswer2, txtAnswer3;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            txtQuestion = (TextView) itemView.findViewById(R.id.txtQuestion);
            txtAnswer1 = (TextView) itemView.findViewById(R.id.txtAnswer1);
            txtAnswer2 = (TextView) itemView.findViewById(R.id.txtAnswer2);
            txtAnswer3 = (TextView) itemView.findViewById(R.id.txtAnswer3);


        }
    }

}
