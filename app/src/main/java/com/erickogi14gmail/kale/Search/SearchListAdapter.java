package com.erickogi14gmail.kale.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.kale.Dictionary.ListPojo;
import com.erickogi14gmail.kale.R;

import java.util.ArrayList;

/**
 * Created by Eric on 10/17/2017.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ListPojo> listPojos;

    public SearchListAdapter(Context context, ArrayList<ListPojo> listPojos) {
        this.context = context;
        this.listPojos = listPojos;
    }

    @Override
    public SearchListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item ,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchListAdapter.MyViewHolder holder, int position) {
        ListPojo listPojo=listPojos.get(position);
        holder.textViewTitle.setText(listPojo.getWord_english());
        holder.textViewDes.setText(listPojo.getWord_english_description());
        holder.textViewT.setText(listPojo.getWord_kale());

    }

    public void updateList(ArrayList<ListPojo> list) {
        listPojos = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return (null != listPojos ? listPojos.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle,textViewDes,textViewT;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewTitle=(TextView)itemView.findViewById(R.id.textView_title);
            textViewDes=(TextView)itemView.findViewById(R.id.textView_des);
            textViewT=(TextView) itemView.findViewById(R.id.textView_title1);
        }
    }
}
