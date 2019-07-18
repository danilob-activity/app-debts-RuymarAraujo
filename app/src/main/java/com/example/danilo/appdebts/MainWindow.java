package com.example.danilo.appdebts;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.danilo.appdebts.adapters.DebtsAdapter;
import com.example.danilo.appdebts.dao.DebtsDAO;
import com.example.danilo.appdebts.database.DataBaseHelper;

public class   MainWindow extends AppCompatActivity {

    RecyclerView mListDebts;
    DebtsAdapter mDebtsAdapter;
    DebtsDAO mDebtsDAO;
    private ConstraintLayout mLayout;
    private SQLiteDatabase mConection;
    private DataBaseHelper mDataHelper;
    private Spinner mSpinnerFilter;

    final String[] mOptionsFilter = {
            "Todas as Dívidas",
            "Dívidas em Aberto",
            "Dividas Pagas",
            "Dívidas por Categoria",
            "Dívidas em Atraso"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mListDebts = findViewById(R.id.recycler_view_debts);
        mLayout = findViewById(R.id.layout);
        createConnection();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mListDebts.setLayoutManager(linearLayoutManager);
        mDebtsAdapter = new DebtsAdapter(mDebtsDAO.listDebts());
        mListDebts.setAdapter(mDebtsAdapter);
        mListDebts.setHasFixedSize(true);

        mSpinnerFilter = findViewById(R.id. spinnerFilter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout. simple_spinner_dropdown_item,
                mOptionsFilter
        );
        mSpinnerFilter.setAdapter(adapter);

        mSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position){
                    case 0:{
                        mDebtsAdapter = new DebtsAdapter(mDebtsDAO.listDividas());
                        mListDebts.setAdapter(mDebtsAdapter);
                        break;
                    }
                    case 3:{
                        mDebtsAdapter = new DebtsAdapter(mDebtsDAO.listDebtsByCategory());
                        mListDebts.setAdapter(mDebtsAdapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void createConnection() {
        try {
            mDataHelper = new DataBaseHelper(this);
            mConection = mDataHelper.getWritableDatabase();
            mDebtsDAO = new DebtsDAO(mConection);
            Snackbar.make(mLayout, R.string.sucess_conection, Snackbar.LENGTH_LONG).show();
        } catch (SQLException e) {
            Snackbar.make(mLayout, e.toString(), Snackbar.LENGTH_LONG).show();
        }
    }

}
