package com.erickogi14gmail.kale.Notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.erickogi14gmail.kale.Dictionary.DbOperation;
import com.erickogi14gmail.kale.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes extends AppCompatActivity {
private TextInputEditText txtTitle,txtContent;
    private Boolean empty=true;
    private DbOperation dbOperations;
    private Boolean saved=false;
    private NotesPojo notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        dbOperations=new DbOperation(Notes.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTitle = (TextInputEditText) findViewById(R.id.edt_title);
        txtContent = (TextInputEditText) findViewById(R.id.edt_note);
        Intent intent=getIntent();
        if(intent.getBooleanExtra("empty",true)) {
             if(intent.getBooleanExtra("hasNote",false)){
                 empty=true;
                 notes = (NotesPojo) intent.getSerializableExtra("data");
                 txtTitle.setText(notes.getNote_title());
                 txtContent.setText(notes.getNote_content());
            }else {

             }
        }else {
            empty=false;
            notes = (NotesPojo) intent.getSerializableExtra("data");
            txtTitle.setText(notes.getNote_title());
            txtContent.setText(notes.getNote_content());

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtTitle.getText().toString().equals("")||!txtContent.getText().toString().equals("")) {
                    if (empty) {
                        NotesPojo note = new NotesPojo();
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd ,yyyy  HH : mm : ss");
                        String timeStamp = simpleDateFormat.format(date);


                        note.setNote_title(txtTitle.getText().toString());
                        note.setNote_content(txtContent.getText().toString());
                        note.setNote_date(timeStamp);

                        if (dbOperations.insertNote(note)) {
                            saved = true;
                            Toast.makeText(Notes.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            saved = false;
                            Toast.makeText(Notes.this, "Error Saving", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        if (dbOperations.updateNote(notes.getEntry_id(), txtTitle.getText().toString(), txtContent.getText().toString())) {
                            Toast.makeText(Notes.this, "Saved", Toast.LENGTH_SHORT).show();
                            saved = true;
                        } else {
                            Toast.makeText(Notes.this, "Error Saving", Toast.LENGTH_SHORT).show();
                            saved = false;
                        }

                    }
                }else {
                    alertDialog("Some Field aren't filled ");
                }
            }
        });
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
       // super.onBackPressed();



        if(saved) {
            this.finish();
        }else {
            alertDialog("Note not saved \n Do you wish to proceed anyway?");
            //AlertDialog alertDialog=new AlertDialog();
          //  Toast.makeText(this, "Note Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
        private void alertDialog( final String message){
        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                      dialog.dismiss();
                      finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        //dialog.dismiss();

                        break;
                }
            }
        };





        AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);

            builder.setMessage(message).setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

    }
}
