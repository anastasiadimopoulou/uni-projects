package com.example.unitestapplication.view.Subject.Monitor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Subject;
import java.util.List;


/*public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.SubjectViewHolder> {
    private List<Subject> subjectList;
    private Context context;

    public SubjectRecyclerViewAdapter(List<Subject> subjectList, Context context) {
        this.subjectList = subjectList;
        this.context = context;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_list_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        final Subject subject = subjectList.get(position);
        holder.nameTextView.setText(subject.getname());
        holder.idTextView.setText(subject.getid());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, idTextView;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txt_item_name);
            idTextView = itemView.findViewById(R.id.txt_item_id);
        }
    }
}*/
public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.SubjectViewHolder> {
    private List<Subject> subjectList;
    private ItemSelectionListener listener;

    public interface ItemSelectionListener {
        void selectsubject(Subject subject);
    }

    public SubjectRecyclerViewAdapter(List<Subject> subjectList, ItemSelectionListener listener) {
        this.subjectList = subjectList;
        this.listener = listener;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        final Subject subject = subjectList.get(position);
        holder.nameTextView.setText(subject.getname());
        holder.idTextView.setText(String.valueOf(subject.getid()));

        holder.itemView.setOnClickListener(v -> listener.selectsubject(subject));
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    /**
     * helps saving all important data when changing screens
     */
    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, idTextView;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txt_item_name);
            idTextView = itemView.findViewById(R.id.txt_item_id);
        }
    }
}
