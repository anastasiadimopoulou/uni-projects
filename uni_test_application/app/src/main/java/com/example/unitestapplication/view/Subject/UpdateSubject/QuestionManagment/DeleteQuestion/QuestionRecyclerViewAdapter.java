package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.view.Subject.Search.SubjectRecyclerViewAdapter;

import java.util.List;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {
    private final List<Question> questionList;//mvalues
    private Context context;
    private final ItemSelectionListener listener;

    public QuestionRecyclerViewAdapter(List<Question> subjectList, ItemSelectionListener listener) {
        this.questionList = subjectList;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //return new ViewHolder(LayoutInflater.from(parent.getContext())
          //      .inflate(R.layout.question_list_item, parent, false));
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_item, parent, false);
        return new ViewHolder(view);
    }


@Override
public void onBindViewHolder(ViewHolder holder, int position) {

    final Question question = questionList.get(position);

    holder.idTextView.setText(String.valueOf(question.getSubjectid()));
    holder.descriptionTextView.setText(question.getDescription());

    holder.descriptionTextView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.selectquestion(question);
        }
    });
}


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    /**
     * helps saving important data when changing screens
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView idTextView;
        public final TextView descriptionTextView;
        private int selectedPosition = -1;



        public ViewHolder(View view) {
            super(view);
            idTextView = view.findViewById(R.id.txt_item_id);
            descriptionTextView = view.findViewById(R.id.txt_item_description);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + idTextView.getText() + "'";
        }
    }

    public interface ItemSelectionListener {
        void selectquestion(Question b);
    }}











