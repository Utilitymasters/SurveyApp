package com.survey.app.view.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.survey.app.R;
import com.survey.app.model.Answer;
import com.survey.app.model.Question;
import com.survey.app.model.QuestionType;
import com.survey.app.model.SubQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rohit.anvekar  on 5/10/2017.
 */

public class SurveyQuestionFragment extends Fragment {
    @BindView(R.id.questionSpinner)
    Spinner questionChoiceSpinner;
    @BindView(R.id.edtQuestion)
    EditText edtQuestion;
    @BindView(R.id.edtSubQuestion)
    EditText edtSubQuestion;
    @BindView(R.id.edtAnswer1)
    EditText edtAnswer1;
    @BindView(R.id.edtAnswer2)
    EditText edtAnswer2;
    @BindView(R.id.edtAnswer3)
    EditText edtAnswer3;
    @BindView(R.id.edtSubAnswer1)
    EditText edtSubAnswer1;
    @BindView(R.id.edtSubAnswer2)
    EditText edtSubAnswer2;
    @BindView(R.id.edtSubAnswer3)
    EditText edtSubAnswer3;
    @BindView(R.id.txtAddSubQuestion)
    TextView txtAddSubQuestion;
    @BindView(R.id.answerList)
    RecyclerView answerRecyclerView;
    @BindView(R.id.subQuestionAnswerList)
    RecyclerView subQuesAnswerRecyclerView;
    @BindView(R.id.subQuestionContainer)
    RelativeLayout subQuestionContainer;
    @BindView(R.id.subQuestionSpinner)
    Spinner subQuestionChoiceSpinner;

    private Unbinder unbinder;
    Question question = new Question();
    SubQuestion subQuestion = new SubQuestion();
    ArrayList<Answer> answerTypeList = new ArrayList<>();

    MySpinnerAdapter questionAdapter, subQuestionAdapter;

    public SurveyQuestionFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_question, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //for updating main question view.
        updateQuestionView(questionAdapter, questionChoiceSpinner, answerRecyclerView, true);

        txtAddSubQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                question.setHasSubQuestion(subQuestionContainer.getVisibility() == View.VISIBLE ? false : true);
                question.setSubQuestion(null);
                txtAddSubQuestion.setText(subQuestionContainer.getVisibility() == View.VISIBLE ? getString(R.string.add_sub_question) : getString(R.string.hide_sub_question));
                subQuestionContainer.setVisibility(subQuestionContainer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                //for updating sub question view
                updateQuestionView(subQuestionAdapter, subQuestionChoiceSpinner, subQuesAnswerRecyclerView, false);
            }
        });


    }

    public void updateQuestionView(MySpinnerAdapter mySpinnerAdapter, Spinner questionChoiceSpinner, final RecyclerView recyclerView, final boolean isMainQuestion) {
        //display question choice to user.
        mySpinnerAdapter = new MySpinnerAdapter(
                getActivity(),
                R.layout.spinview,
                Arrays.asList(getResources().getStringArray(R.array.question_type))
        );
        questionChoiceSpinner.setAdapter(mySpinnerAdapter);

        // set answer on basis of question type selected by user.
        answerTypeList = new QuestionType().getAnswerTypeList(QuestionType.SINGLE_CHOICE);

        // on user selection update displayed answer type list.
        questionChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (isMainQuestion && subQuestionContainer.getVisibility() == View.VISIBLE) {
                    txtAddSubQuestion.setText(getString(R.string.add_sub_question));
                    subQuestionContainer.setVisibility(subQuestionContainer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }

                if (isMainQuestion) {
                    txtAddSubQuestion.setVisibility(position == QuestionType.SINGLE_CHOICE ? View.VISIBLE : View.GONE);

                    updateQuestionType(position);
                } else {

                    updateSubQuestionType(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void updateQuestionType(int quesType) {
        if (quesType == QuestionType.SINGLE_CHOICE) {
            edtAnswer1.setVisibility(View.VISIBLE);
            edtAnswer1.setLines(1);
            edtAnswer2.setVisibility(View.GONE);
            edtAnswer3.setVisibility(View.GONE);
        } else if (quesType == QuestionType.MULTIPLE_CHOICE) {
            edtAnswer1.setVisibility(View.VISIBLE);
            edtAnswer1.setLines(1);
            edtAnswer2.setVisibility(View.VISIBLE);
            edtAnswer3.setVisibility(View.VISIBLE);
        } else if (quesType == QuestionType.MULTILINE_CHOICE) {
            edtAnswer1.setVisibility(View.VISIBLE);
            edtAnswer1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            edtAnswer1.setLines(4);
            edtAnswer2.setVisibility(View.GONE);
            edtAnswer3.setVisibility(View.GONE);
        }

    }

    private void updateSubQuestionType(int quesType) {
        if (quesType == QuestionType.SINGLE_CHOICE) {
            edtSubAnswer1.setVisibility(View.VISIBLE);
            edtSubAnswer1.setLines(1);
            edtSubAnswer2.setVisibility(View.GONE);
            edtSubAnswer3.setVisibility(View.GONE);
        } else if (quesType == QuestionType.MULTIPLE_CHOICE) {
            edtSubAnswer1.setVisibility(View.VISIBLE);
            edtSubAnswer1.setLines(1);
            edtSubAnswer2.setVisibility(View.VISIBLE);
            edtSubAnswer3.setVisibility(View.VISIBLE);
        } else if (quesType == QuestionType.MULTILINE_CHOICE) {
            edtSubAnswer1.setVisibility(View.VISIBLE);
            edtSubAnswer1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            edtSubAnswer1.setLines(4);
            edtSubAnswer2.setVisibility(View.GONE);
            edtSubAnswer3.setVisibility(View.GONE);
        }

    }

    public Question getUpdatedQuestionDetail() {

        question.setQuestion(edtQuestion.getText().toString());
        if (question.isHasSubQuestion()) {
            subQuestion.setSubQuestion(edtSubQuestion.getText().toString());
            subQuestion.setSubQuesAnswerList(getSubAnswerTypeList());
            question.setSubQuestion(subQuestion);
        }
        question.setAnswerList(getAnswerTypeList());

        return question;

    }

    public Question getQuestion() {
        return question;
    }


    private ArrayList<Answer> getAnswerTypeList() {
        ArrayList<Answer> answers = new ArrayList<>();
        if (edtAnswer1.getVisibility() == View.VISIBLE) {
            answers.add(new Answer(edtAnswer1.getText().toString()));
        }

        if (edtAnswer2.getVisibility() == View.VISIBLE) {
            answers.add(new Answer(edtAnswer2.getText().toString()));
        }

        if (edtAnswer3.getVisibility() == View.VISIBLE) {
            answers.add(new Answer(edtAnswer3.getText().toString()));
        }
        return answers;
    }

    private ArrayList<Answer> getSubAnswerTypeList() {
        ArrayList<Answer> answers = new ArrayList<>();
        if (edtSubAnswer1.getVisibility() == View.VISIBLE) {
            answers.add(new Answer(edtSubAnswer1.getText().toString()));
        }

        if (edtSubAnswer2.getVisibility() == View.VISIBLE) {
            answers.add(new Answer(edtSubAnswer2.getText().toString()));
        }

        if (edtSubAnswer3.getVisibility() == View.VISIBLE) {
            answers.add(new Answer(edtSubAnswer3.getText().toString()));
        }
        return answers;
    }


    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/comicsanslight.ttf");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }


    }


}
