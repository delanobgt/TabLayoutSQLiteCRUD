package com.example.tablayoutsqlitecrud.adapters;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tablayoutsqlitecrud.R;
import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.models.Student;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Student student = Store.studentList.get(position);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.setDetailSelectedStudentIndex(position);
                Store.tabLayout.getTabAt(1).select();
            }
        });
        holder.tvFullname.setText(student.fullname);
        holder.tvGender.setText(student.gender);
        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.setEditSelectedStudentIndex(position);
                Store.tabLayout.getTabAt(2).select();
            }
        });
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return Store.studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView tvFullname;
        TextView tvGender;
        ImageButton ibEdit;
        ImageButton ibDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            tvFullname = itemView.findViewById(R.id.tv_fullname);
            tvGender = itemView.findViewById(R.id.tv_gender);
            ibEdit = itemView.findViewById(R.id.ib_edit);
            ibDelete= itemView.findViewById(R.id.ib_delete);
        }
    }
}
