package com.example.unitestapplication.view.showSubject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unitestapplication.R;
import com.example.unitestapplication.domain.Subject;

import java.util.List;

public class showSubjectRecyclerViewAdapter extends RecyclerView.Adapter<showSubjectRecyclerViewAdapter.ViewHolder> {

    private final List<Subject> mValues;
    private final ItemSelectionListener listener;

    public showSubjectRecyclerViewAdapter(List<Subject> items, ItemSelectionListener listener) {
        mValues = items;
        this.listener = listener;
    }

    /**
     * create and return viewholder
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_list_item, parent, false));
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Subject currentItem = mValues.get(position);
        holder.txtItemId.setText(String.valueOf(currentItem.getid()));
        holder.txtItemTitle.setText(currentItem.getname());
        holder.txtItemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectsubject(currentItem);
            }
        });

    }

    /**
     * returns list size
     * @return int
     */

    @Override
    public int getItemCount() {

        return mValues.size();
    }


    /**
     * helps showing subjects important data
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtItemId;
        public final TextView txtItemTitle;

        public ViewHolder(View view) {
            super(view);
            txtItemId = view.findViewById(R.id.txt_item_id);
            txtItemTitle = view.findViewById(R.id.txt_item_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtItemTitle.getText() + "'";
        }
    }


    public interface ItemSelectionListener {
        void selectsubject(Subject b);
    }
}

