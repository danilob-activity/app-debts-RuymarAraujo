package com.example.danilo.appdebts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class insert_debts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_debts);

        getSupportActionBar().setDisplayHomeAsUpEnabled( true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled( true); //Ativar o botão
        getSupportActionBar().setTitle(R.string.titleInsert);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu. menu_add_debts,menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) { //Botão na ToolBar
//        switch (item.getItemId()) {
//            case android.R.id. home:
//
//        }
}
