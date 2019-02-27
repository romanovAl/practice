package com.example.samsung.practice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CommentsRecycleAdapter extends
        RecyclerView.Adapter<CommentsRecycleAdapter.CommentsVH> {

    private ArrayList<Comment>commentsList;

    public CommentsRecycleAdapter(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public CommentsRecycleAdapter.CommentsVH  onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.comment_item, viewGroup, false);

        return new CommentsRecycleAdapter.CommentsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsRecycleAdapter.CommentsVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CommentsVH extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;

        public CommentsVH(@NonNull final View itemView){
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView5 = itemView.findViewById(R.id.textView5);
        }
    }

}





