package com.example.danilo.appdebts;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.danilo.appdebts.classes.Category;
import com.example.danilo.appdebts.classes.Debts;
import com.example.danilo.appdebts.dao.CategoryDAO;
import com.example.danilo.appdebts.dao.DebtsDAO;
import com.example.danilo.appdebts.database.DataBaseHelper;

public class TelaInicial extends AppCompatActivity {

    private SQLiteDatabase mConection;
    private DataBaseHelper mDataHelper;
    private ConstraintLayout mLayout;
    private CategoryDAO mCategoryDAO;
    private DebtsDAO mDebtsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        mLayout = findViewById(R.id.layout);

        createConnection();
    }


    private void createConnection() {
        try {
            mDataHelper = new DataBaseHelper(this);
            mConection = mDataHelper.getWritableDatabase();
            Snackbar.make(mLayout, R.string.sucess_conection, Snackbar.LENGTH_LONG).show();
        } catch (SQLException e) {
            Snackbar.make(mLayout, e.toString(), Snackbar.LENGTH_LONG).show();
        }
    }

    public void populateDataBase(){
        createConnection();
        Category cat1 = new Category("Casa");
        cat1 = mCategoryDAO.insert(cat1);

        Category cat2 = new Category("Comida");
        cat2 = mCategoryDAO.insert(cat2);

        Category cat3 = new Category("Colegio");
        cat3 = mCategoryDAO.insert(cat3);

        Debts debt1 = new Debts(cat1,(float)79.80, "produtos de limpeza");
        debt1 = mDebtsDAO.insert(debt1);

        Debts debt2 = new Debts(cat2,(float)50.80, "Mistura" , 20/8/2019 ,);
        debt2 = mDebtsDAO.insert(debt2);

        Debts debt3 = new Debts(cat2,(float)20.80, "Bolos", 25/8/2019 ,);
        debt3 = mDebtsDAO.insert(debt3);

        Debts debt4 = new Debts(cat3,(float)100.00, "Filho1", 30/8/2019,);
        debt4 = mDebtsDAO.insert(debt4);

        Debts debt5 = new Debts(cat2,(float)30.00, "Cerveja" , 20/7/2019, );
        debt5 = mDebtsDAO.insert(debt5);

        Debts debt6 = new Debts(cat2,(float)10.00,"Pao" "14/08/2019");
        debt6 = mDebtsDAO.insert(debt6);

        Debts debt7 = new Debts(cat3,(float)150.00, "Filho2" , 30/7/2019 ,);
        debt7 = mDebtsDAO.insert(debt7);
    }

}
