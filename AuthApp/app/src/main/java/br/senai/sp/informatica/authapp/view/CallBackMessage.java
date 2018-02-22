package br.senai.sp.informatica.authapp.view;

import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CallBackMessage implements DatabaseReference.CompletionListener{
    private String msg;

    public CallBackMessage(String msg){ this.msg = msg;}

    @Override
    public void onComplete(DatabaseError error, DatabaseReference reference){
        if(error != null){
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

}
