package com.erickogi14gmail.kale.Learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.R;
import com.erickogi14gmail.kale.utill.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Eric on 10/19/2017.
 */

public class FragmentLearnMain extends Fragment {
    private View view;
    private ArrayList<LessonPojo> lessonPojos;
    private LessonAdapter lessonAdapter;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private DbOperation dbOperation;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_learn_main,container,false);
        dbOperation=new DbOperation(getContext());
        lessonPojos=dbOperation.searchLessons("");
        initViews();
        return view;
    }
    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        lessonAdapter=new LessonAdapter(getContext(),lessonPojos);


        lessonAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lessonAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               fragment=new FragmentLesson();
                popOutFragments();
                setUpView();

            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));


        //registerForContextMenu(recyclerView);



        if(lessonAdapter.getItemCount()<1){

            for(int a=0;a<8;a++) {
                LessonPojo lessonPojo = new LessonPojo();
                lessonPojo.setLesson_id(String.valueOf(a));
                String ab=(a+1)+"0";
                lessonPojo.setProgress(Integer.valueOf(ab));
                lessonPojo.setTitle("Lesson"+(a+1));
                if(dbOperation.insertLessons(lessonPojo)){
                    lessonAdapter.updateList(dbOperation.searchLessons(""));
                   // Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    void setUpView() {
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
                    .addToBackStack(null).commit();
        }

    }

    void popOutFragments() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

}
