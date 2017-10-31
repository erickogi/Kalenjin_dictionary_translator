package com.erickogi14gmail.kale.Notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.R;
import com.erickogi14gmail.kale.utill.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Eric on 10/19/2017.
 */

public class FragmentNotes extends Fragment {
    private View view;
    private SearchView searchView;
    private DbOperation dbOperation;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private  NotesAdapter listAdapter;
    private  ArrayList<NotesPojo> notesPojos;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_notes,container,false);
        //view=inflater.inflate(R.layout.fragment_search,container,false);
        dbOperation=new DbOperation(getContext());
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        notesPojos=dbOperation.searchNotes("");
//        Log.d("fav_cliked",listPojos.toString());
         listAdapter=new NotesAdapter(getContext(),notesPojos);
        listAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);
        FloatingActionButton floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab);
        floatingActionButton.show();
        floatingActionButton.setImageResource(R.drawable.ic_note_add_black_24dp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Notes.class);
                intent.putExtra("empty",true);

                startActivity(intent);
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getActivity(),Notes.class);
                intent.putExtra("data",notesPojos.get(position));
                intent.putExtra("empty",false);

                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                alertDialog("You are about to delete a note\n Continue",notesPojos.get(position).getEntry_id());

            }
        }));

        getActivity().setTitle("Notes");
        LinearLayout linearLayoutSwitch=(LinearLayout)getActivity().findViewById(R.id.layout_switch);
        linearLayoutSwitch.setVisibility(View.GONE);

        final RelativeLayout relativeLayoutSearch=getActivity().findViewById(R.id.relative_layout_search);
        relativeLayoutSearch.setPadding(16,2,16,20);
        relativeLayoutSearch.setVisibility(View.GONE);




        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setCollapsible(false);
        toolbar.setNestedScrollingEnabled(false);


        return view;
    }

    @Override
    public void onResume() {
       notesPojos=dbOperation.searchNotes("");
//        Log.d("fav_cliked",listPojos.toString());
        //listAdapter=new NotesAdapter(getContext(),notesPojos);
        listAdapter.updateList(notesPojos);
        super.onResume();
    }
    private void alertDialog(final String message, final int id){
        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                       if( dbOperation.deleteNote(id)) {
                           listAdapter.updateList(dbOperation.searchNotes(""));
                           dialog.dismiss();
                       }
                       else {
                           Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                       }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                      //  dialog.dismiss();

                        break;
                }
            }
        };





        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(message).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }
}
