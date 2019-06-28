package com.example.danilo.appdebts.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.danilo.appdebts.classes.Category;
import com.example.danilo.appdebts.classes.Debts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 27/06/19.
 */

public class DebtsDAO {
    private SQLiteDatabase mConnection;

    public DebtsDAO(SQLiteDatabase conection){
        mConnection = conection;
    }




    public Debts insert(Debts div){
        ContentValues contentValues = new ContentValues();
        contentValues.put("valor", div.getValor());
        contentValues.put("descricao", div.getDescricao());
        contentValues.put("data_vencimento", div.getDataVencimento());
        contentValues.put("data_pagamento", div.getDataPagamento());
        contentValues.put("cod_cat", div.getCategoria().getId());
        long id = mConnection.insertOrThrow("dividas", null, contentValues);
        Log.d("DebtsDAO", "Inserção realizada com sucesso!");
        div.setId(id);
        return div;
    }

    public void remove(int id){
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        mConnection.delete("dividas","id = ?",params);

    }

    public void alter(Debts div){
        ContentValues contentValues = new ContentValues();
        contentValues.put("valor", div.getValor());
        contentValues.put("descricao", div.getDescricao());
        contentValues.put("data_vencimento", div.getDataVencimento());
        contentValues.put("data_pagamento", div.getDataPagamento());
        contentValues.put("cod_cat", div.getCategoria().getId());
        String[] params = new String[1];
        params[0] = String.valueOf(div.getId());
        mConnection.update("dividas",contentValues, "id = ?",params);

    }

    public List<Debts> listDividas(){
        List<Debts> dividas = new ArrayList<Debts>();
        Cursor result = mConnection.rawQuery("Select id, tipo from dividas",null);
        if(result.getCount()>0){
            result.moveToFirst();
            CategoryDAO categoryDAO = new CategoryDAO(mConnection);
            do{
                Debts div = new Debts();
                Category cat = categoryDAO.getCategory(result.getInt(result.getColumnIndexOrThrow("cod_cat")));
                div.setId(result.getInt(result.getColumnIndexOrThrow("id")));
                div.setValor(result.getFloat(result.getColumnIndexOrThrow("valor")));
                div.setDescricao(result.getString(result.getColumnIndexOrThrow("descricao")));
                div.setDataVencimento(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
                div.setDataPagamento(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
                div.setCategoria(cat);
                dividas.add(div);
                Log.d("DebtsDAO","Listando: "+ div.getId()+" - "+ div.getDescricao()+" - " + div.getValor());
            }while(result.moveToNext());
            result.close();
        }
        return dividas;
    }

    public Debts getDebts(int id){
        Debts div = new Debts();
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        CategoryDAO categoryDAO = new CategoryDAO(mConnection);
        Cursor result = mConnection.rawQuery("Select * from categoria where id='?'",params);
        if(result.getCount()>0){
            result.moveToFirst();
            Category cat = categoryDAO.getCategory(result.getInt(result.getColumnIndexOrThrow("cod_cat")));
            div.setId(result.getInt(result.getColumnIndexOrThrow("id")));
            div.setValor(result.getFloat(result.getColumnIndexOrThrow("valor")));
            div.setDescricao(result.getString(result.getColumnIndexOrThrow("descricao")));
            div.setDataVencimento(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
            div.setDataPagamento(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
            div.setCategoria(cat);
            result.close();
            return div;
        }

        return null;

    }



}
