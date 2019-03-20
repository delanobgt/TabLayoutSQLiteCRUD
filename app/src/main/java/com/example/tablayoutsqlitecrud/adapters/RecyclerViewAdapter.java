package com.example.tablayoutsqlitecrud.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tablayoutsqlitecrud.R;
import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.models.Student;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Student student = Store.getStore().studentList.get(position);
        holder.tvFullname.setText(student.fullname);
        holder.tvGender.setText(student.gender);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.getStore().tabLayout.getTabAt(1).select();
                Student student = Store.getStore().studentList.get(position);
                Store.getStore().detailFragment.setStudent(student);
            }
        });
        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.getStore().tabPagerAdapter.tab3Title = "Edit";
                Store.getStore().tabPagerAdapter.notifyDataSetChanged();
                Store.getStore().tabLayout.getTabAt(2).select();
                Student student = Store.getStore().studentList.get(position);
                Store.getStore().modifyFragment.setEditForm(student);
            }
        });
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Student Confirmation")
                        .setMessage(String.format("Delete %s ?", student.fullname))
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Store.getStore().deleteStudentById(student.id);
                                Store.getStore().recyclerViewAdapter.notifyDataSetChanged();
                                Toast.makeText(context, "Student deleted.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Store.getStore().studentList.size();
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
