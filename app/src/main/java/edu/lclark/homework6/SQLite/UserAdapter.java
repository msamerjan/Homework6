package edu.lclark.homework6.SQLite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.homework6.R;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> mUsers;


    public UserAdapter(ArrayList<User> students) {
        mUsers = students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.mIdTextView.setText(String.valueOf(user.getUser()));
        holder.mNameTextView.setText(user.getPins());
        holder.mNetworthTextView.setText(String.valueOf(user.getNetWorth()));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addUser(User user) {
        mUsers.add(user);
        notifyItemInserted(mUsers.size()-1);
    }

    public void setStudents(ArrayList<User> students) {
        mUsers = students;
        notifyDataSetChanged();
    }
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_student_id_textview)
        TextView mIdTextView;
        @Bind(R.id.row_student_name_textview)
        TextView mNameTextView;
        @Bind(R.id.row_student_networth_textview)
        TextView mNetworthTextView;

        public StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
