package com.erickogi14gmail.kale.Search;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.Dictionary.ListPojo;
import com.erickogi14gmail.kale.R;
import com.erickogi14gmail.kale.utill.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Eric on 10/19/2017.
 */

public class FragmentFavorites extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private View view;
    private SearchView searchView;
    private DbOperation dbOperation;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_favourites,container,false);
        //view=inflater.inflate(R.layout.fragment_search,container,false);
        dbOperation=new DbOperation(getContext());
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        final ArrayList<ListPojo> listPojos=dbOperation.searchFavorites("");
//        Log.d("fav_cliked",listPojos.toString());
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
                createMenu(view,listPojos.get(position));

            }
        }));

        getActivity().setTitle("Favorites");
        LinearLayout linearLayoutSwitch=(LinearLayout)getActivity().findViewById(R.id.layout_switch);
        linearLayoutSwitch.setVisibility(View.GONE);

        final RelativeLayout relativeLayoutSearch=getActivity().findViewById(R.id.relative_layout_search);
        relativeLayoutSearch.setPadding(16,2,16,20);
        relativeLayoutSearch.setVisibility(View.VISIBLE);




        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setCollapsible(false);
        toolbar.setNestedScrollingEnabled(false);


        FloatingActionButton floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();

        return view;
    }
    private int menuentry_id;private ListPojo menuListPojo;
    private int getMenuentry_id(){
        return menuentry_id;
    }
    private ListPojo getMenuEntryListPojo(){
        return menuListPojo;
    }
    private void createMenu(View v,ListPojo listPojo){
        menuentry_id=listPojo.getEntry_id();
        menuListPojo=listPojo;
        PopupMenu popupMenu=new PopupMenu(getContext(),v);
        popupMenu.setOnMenuItemClickListener(this);
        MenuInflater inflater=popupMenu.getMenuInflater();
        inflater.inflate(R.menu.context_menu,popupMenu.getMenu());
        popupMenu.show();




    }

    /**
     * This method will be invoked when a menu item is clicked if the item
     * itself did not already handle the event.
     *
     * @param item the menu item that was clicked
     * @return {@code true} if the event was handled, {@code false}
     * otherwise
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId()==R.id.nav_remove){
            dbOperation.removeFromFavorite(getMenuentry_id());
        }else if(item.getItemId()==R.id.nav_share){
            String wordA1=" English Word : "+getMenuEntryListPojo().getWord_english() +"\n Defination : "+getMenuEntryListPojo().getWord_english_description();
            String wordA2=" Kalenjin Translation : "+getMenuEntryListPojo().getWord_kale() +"\n Defination : "+getMenuEntryListPojo().getWord_kale_description();

            String share="Translate : "+wordA1+"\n"+wordA2;
            shareEvent(share);

        }else if(item.getItemId()==R.id.nav_note){

        }
        return false;
    }
    private void shareEvent(String text) {
        Intent in = new Intent();
        in.setAction(Intent.ACTION_SEND);
        in.putExtra(Intent.EXTRA_TEXT, text+"\n Shared From My App ");
        in.setType("text/plain");
        startActivity(in);
    }

    private void copyText(String text) {
        ClipboardManager clip = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Product", text);
        clip.setPrimaryClip(clipData);
        Toast.makeText(getContext(), "Copied To Clipboard .", Toast.LENGTH_SHORT).show();
    }
}
