package com.erickogi14gmail.kale.Search;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.Dictionary.WordPojo;
import com.erickogi14gmail.kale.Notes.Notes;
import com.erickogi14gmail.kale.Notes.NotesPojo;
import com.erickogi14gmail.kale.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class CardFragment extends Fragment implements TextToSpeech.OnInitListener,View.OnClickListener {

    private CardView cardView;
    private TextView txtWord1,txtWord2,txtDes1,txtDes2;
    private ImageView imgShare1,imgShare2,imgSpeak1,imgSpeak2,imgShareAll,imgFavAll,imgNoteAll;
    private TextToSpeech textToSpeech;
    private WordPojo wordPojo;

    private DbOperation dbOperation;
    public static Fragment getInstance(int position, WordPojo wordPojoo) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();


        args.putSerializable("data",wordPojoo);

        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_item, container, false);
        dbOperation=new DbOperation(getContext());
        textToSpeech=new TextToSpeech(getContext(),this);



        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        txtWord1=(TextView)view.findViewById(R.id.txt_word1);
        txtWord2=(TextView)view.findViewById(R.id.txt_word2);
        txtDes1=(TextView)view.findViewById(R.id.des_word1);
        txtDes2=(TextView)view.findViewById(R.id.des_word2);

        imgShare1=view.findViewById(R.id.share_word1);
        imgShare2=view.findViewById(R.id.share_word2);
        imgShareAll=view.findViewById(R.id.share_all);
        imgSpeak1=view.findViewById(R.id.speak_word1);
        imgSpeak2=view.findViewById(R.id.speak_word2);
        imgFavAll=view.findViewById(R.id.favorite_all);
        imgNoteAll=view.findViewById(R.id.note_all);

        imgShare1.setOnClickListener(this);
        imgShare2.setOnClickListener(this);
        imgFavAll.setOnClickListener(this);
        imgShareAll.setOnClickListener(this);
        imgNoteAll.setOnClickListener(this);

        imgSpeak2.setVisibility(View.GONE);
        imgSpeak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(txtWord1.getText().toString()+","+txtDes1.getText().toString());
            }
        });





        wordPojo=(WordPojo)getArguments().getSerializable("data");
        txtWord1.setText(wordPojo.getWord_english());
        txtWord2.setText(wordPojo.getWord_kale());
        txtDes1.setText(wordPojo.getWord_english_description());
        txtDes2.setText(wordPojo.getWord_kale_description());

      //  getActivity().setTitle(txtWord1.getText());





        return view;
    }


    private void speak(String text){
        if(text.length()<1){
            //textToSpeech.speak("Not content",TextToSpeech.QUEUE_FLUSH,null);
        }else {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                ttsGreater21(text);
            }else {
                ttsUnder20(text);
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text){
        HashMap<String,String> map=new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"MeageId");
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,map);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text){
        String utteranceId=getContext().hashCode()+"";
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,utteranceId);
    }








    public CardView getCardView() {
        return cardView;
    }


    /**
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS){
            int result=textToSpeech.setLanguage(Locale.ENGLISH);
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported");
               // imgSpeak2.setVisibility(View.GONE);
                imgSpeak1.setVisibility(View.GONE);
            }else {
               // imgSpeak2.setVisibility(View.VISIBLE);
                imgSpeak1.setVisibility(View.VISIBLE);
            }
        }else {
            Log.e("TTS","Language not initializes");
        }
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
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
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_word1:
             String word=" English Word : "+txtWord1.getText().toString() +"\n Defination : "+txtDes1.getText().toString();
                shareEvent(word);

                break;
            case R.id.share_word2:
                String word2=" Kalenjin Translation : "+txtWord2.getText().toString() +"\n Defination : "+txtDes2.getText().toString();
                shareEvent(word2);
                break;
            case R.id.share_all:
                String wordA1=" English Word : "+txtWord1.getText().toString() +"\n Defination : "+txtDes1.getText().toString();
                String wordA2=" Kalenjin Translation : "+txtWord2.getText().toString() +"\n Defination : "+txtDes2.getText().toString();

                String share="Translate : "+wordA1+"\n"+wordA2;
                shareEvent(share);

                break;
            case R.id.favorite_all:
                 int entry_id=wordPojo.getEntry_id();
                if(dbOperation.setFavorite(entry_id,"1")){
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.note_all:
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd ,yyyy  HH : mm : ss");
                String timeStamp = simpleDateFormat.format(date);
                String wordAn1=" English Word : "+txtWord1.getText().toString() +"\n Defination : "+txtDes1.getText().toString();
                String wordAn2=" Kalenjin Translation : "+txtWord2.getText().toString() +"\n Defination : "+txtDes2.getText().toString();

               // String share="Translate : "+wordA1+"\n"+wordA2;

                NotesPojo notesPojo=new NotesPojo();
                notesPojo.setNote_date(timeStamp);
                notesPojo.setNote_title("Words "+txtWord1.getText().toString()+" "+txtWord2.getText().toString());
                notesPojo.setNote_content("Translation : "+wordAn1+"\n"+wordAn2);
                Intent intent=new Intent(getActivity(),Notes.class);
                intent.putExtra("data",notesPojo);
                intent.putExtra("empty",true);
                intent.putExtra("hasNote",true);

                startActivity(intent);

                break;
        }

    }
}
