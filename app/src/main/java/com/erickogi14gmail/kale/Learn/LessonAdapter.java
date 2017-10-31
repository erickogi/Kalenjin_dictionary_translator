package com.erickogi14gmail.kale.Learn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.kale.R;

import java.util.ArrayList;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;

/**
 * Created by Eric on 10/19/2017.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<LessonPojo> modelList;

    public LessonAdapter(Context context, ArrayList<LessonPojo> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public LessonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item ,parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    @Override
    public void onBindViewHolder(LessonAdapter.MyViewHolder holder, int position) {
        LessonPojo lessonPojo=modelList.get(position);
        holder.txtTitle.setText(lessonPojo.getTitle());

        holder.circleProgressView.setSpinBarColor(context.getResources().getColor(R.color.colorAccent));
        holder.circleProgressView.setValue(Float.valueOf(lessonPojo.getProgress()));
        holder.circleProgressView.setTextMode(TextMode.TEXT);
        holder.circleProgressView.setAutoTextSize(true);
        holder.circleProgressView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        holder.circleProgressView.setRimColor(context.getResources().getColor(R.color.colorPrimaryDark));
        holder.circleProgressView.setText(lessonPojo.getProgress()+"% Done");
        holder.circleProgressView.setRimWidth(2);
        holder.circleProgressView.setBarWidth(10);
        holder.circleProgressView.setSpinBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        holder.circleProgressView.setBarColor(context.getResources().getColor(R.color.colorPrimary));



    }

    public void updateList(ArrayList<LessonPojo> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private CircleProgressView circleProgressView;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.txt_lesson_title);
            circleProgressView=(CircleProgressView)itemView.findViewById(R.id.circleProgress);
        }
    }
}
