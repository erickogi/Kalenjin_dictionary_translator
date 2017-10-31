package com.erickogi14gmail.kale.Search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.Dictionary.ListPojo;
import com.erickogi14gmail.kale.Dictionary.WordPojo;
import com.erickogi14gmail.kale.R;
import com.erickogi14gmail.kale.utill.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Eric on 10/17/2017.
 */

public class FragmentSearch extends Fragment {
    private View view;
    private SearchView searchView;
    private DbOperation dbOperation;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private  Switch s;

    public  void insertItem(){
        dbOperation=new DbOperation(getContext());
        for(int a=0;a<20;a++){
            WordPojo wordPojo=new WordPojo();

            wordPojo.setWord_english("Hello Word"+a);
            wordPojo.setWord_kale("Chamge"+a);
            wordPojo.setWord_kale_description("Its a Noun,This is the description of the word  in kalenin. \nAn example of usage of this word in a sentense is :\n\n This an example of the kalenjin word as used in as sentense' \nRelated words : related word 1, Related word 2, Related word 3");
            wordPojo.setWord_english_description("Its a Noun,\nThis is the description of the word in English. \n Here is an example of usage of the word in a sentense \n\n \b 'An example of the English word as used in a sentence' \n Related words : related word1 , Related word 2");
            wordPojo.setWord_others("This is a greeting");
            wordPojo.setWord_favourite_status("0");
            wordPojo.setWord_kiswahili("Habari");
            wordPojo.setWord_view_count(1);
            wordPojo.setWord_view_last("12/12/2016");

            dbOperation.insertWord(wordPojo);


        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_search,container,false);
        dbOperation=new DbOperation(getContext());
        getActivity().setTitle("Search");

        LinearLayout linearLayoutSwitch=(LinearLayout)getActivity().findViewById(R.id.layout_switch);
        linearLayoutSwitch.setVisibility(View.VISIBLE);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        final ArrayList<ListPojo> listPojos=dbOperation.searchWords("",1);
        final SearchListAdapter searchListAdapter=new SearchListAdapter(getContext(),listPojos);
        searchListAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchListAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                int pos=listPojos.get(position).getEntry_id();
                Intent intent=new Intent(getActivity(),ResultsActivity.class);
                intent.putExtra("position",pos);
                intent.putExtra("word",listPojos.get(position).getWord_english()+"<>"+listPojos.get(position).getWord_kale());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        if(searchListAdapter.getItemCount()<1){
            insertItem();
            searchListAdapter.updateList(dbOperation.searchWords("",1));
        }
         s=getActivity().findViewById(R.id.btn_switch);
        final TextView txtEnglis=getActivity().findViewById(R.id.txt_eng);
        final TextView txtKale=getActivity().findViewById(R.id.txt_kale);
        txtEnglis.setEnabled(true);
        txtEnglis.setTextSize(20);
        txtKale.setTextSize(16);
        txtKale.setEnabled(false);
        final SearchView search =  getActivity().findViewById(R.id.search_bar);


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.isChecked()){
                    txtKale.setEnabled(true);
                    txtEnglis.setTextSize(16);
                    txtEnglis.setEnabled(false);
                    txtKale.setTextSize(20);
                    searchListAdapter.updateList(dbOperation.searchWords(search.getQuery().toString(),2));


                }else {
                    txtKale.setTextSize(16);
                    txtKale.setEnabled(false);
                    txtEnglis.setEnabled(true);
                    txtEnglis.setTextSize(20);
                    searchListAdapter.updateList(dbOperation.searchWords(search.getQuery().toString(),1));


                }

            }
        });







        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);

           final RelativeLayout relativeLayoutSearch=getActivity().findViewById(R.id.relative_layout_search);
            search.setQueryHint("Search");
            relativeLayoutSearch.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);




            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    search.setIconified(false);
                }
            });

            search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


                @Override
                public boolean onQueryTextSubmit(String query) {


                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {


                  //  Toast.makeText(getContext(), ""+newText, Toast.LENGTH_SHORT).show();
                    int lang=1;
                    if(s.isChecked()){
                        lang=2;
                    }
                    //dbOperation.searchWords(newText,lang);
                    searchListAdapter.updateList(dbOperation.searchWords(newText,lang));



                    //search.setQuery("",false);
                   // relativeLayoutSearch.setVisibility(View.GONE);


                    return false;
                }
            });

        }
        FloatingActionButton floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        return view;
    }
}
