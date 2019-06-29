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

    public void populateDataBase() {
        createConnection();
        Category cat1 = new Category("Casa");
        cat1 = mCategoryDAO.insert(cat1);

        Category cat2 = new Category("Comida");
        cat2 = mCategoryDAO.insert(cat2);

        Category cat3 = new Category("Colegio");
        cat3 = mCategoryDAO.insert(cat3);

        Debts debt1 = new Debts(cat2, "Lagosta", (float) 230.0, "15/07/2019", "10/07/2019");
        debt1 = mDebtsDAO.insert(debt1);

        Debts debt2 = new Debts(cat2, "Coxinha", (float) 5.0, "5/07/2019", "3/07/2019");
        debt2 = mDebtsDAO.insert(debt2);

        Debts debt3 = new Debts(cat1, "Produtos de limpeza", (float) 100.0, "10/08/2019", "12/08/2019");
        debt3 = mDebtsDAO.insert(debt3);

        Debts debt4 = new Debts(cat3, "Filho1", (float) 350.0, "20/08/2019", "16/08/2019");
        debt4 = mDebtsDAO.insert(debt4);

        Debts debt5 = new Debts(cat2, "Carne", (float) 180.0, "25/08/2019", "20/08/2019");
        debt5 = mDebtsDAO.insert(debt5);

        Debts debt6 = new Debts(cat3, "Filho2", (float) 500.0, "28/07/2019", "25/07/2019");
        debt6 = mDebtsDAO.insert(debt6);

        Debts debt7 = new Debts(cat2, "Pizza", (float) 85.0, "30/07/2019", "02/08/2019");
        debt7 = mDebtsDAO.insert(debt7);

        Debts debt8 = new Debts(cat1, "Internet", (float) 130.0, "30/08/2019", "20/08/2019");
        debt8 = mDebtsDAO.insert(debt8);
    }

}
