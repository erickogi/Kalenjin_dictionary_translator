package com.erickogi14gmail.kale.Notes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.kale.R;

import java.util.ArrayList;

/**
 * Created by Eric on 10/19/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<NotesPojo> modelList;

    public NotesAdapter(Context context, ArrayList<NotesPojo> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public NotesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item ,parent, false);
        return new MyViewHolder(itemView);
    }
    public void updateList(ArrayList<NotesPojo> list) {
        modelList = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(NotesAdapter.MyViewHolder holder, int position) {
     NotesPojo notesPojo=modelList.get(position);
        holder.title.setText(notesPojo.getNote_title());
        holder.date.setText(notesPojo.getNote_date());
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,date;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.note_title);
            date=(TextView)itemView.findViewById(R.id.note_date);

        }
    }
}
