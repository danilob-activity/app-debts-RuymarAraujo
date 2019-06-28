package com.example.danilo.appdebts.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.danilo.appdebts.classes.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 27/06/19.
 */

public class CategoryDAO {
    private SQLiteDatabase mConnection;



    public CategoryDAO(SQLiteDatabase conection){
        mConnection = conection;
    }

    public Category insert(Category cat){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tipo", cat.getTipo());
        long id = mConnection.insertOrThrow("categoria", null, contentValues);
        Log.d("CategoryDAO", "Inserção realizada com sucesso!");
        cat.setId(id);
        return cat;
    }

    public void remove(int id){
       String[] params = new String[1];
       params[0] = String.valueOf(id);
       mConnection.delete("categoria","id = ?",params);

    }




    public void alter(Category cat){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tipo",cat.getTipo());
        String[] params = new String[1];
        params[0] = String.valueOf(cat.getId());
        mConnection.update("categoria",contentValues, "id = ?",params);

    }



    public List<Category> listCategories(){
        List<Category> categories = new ArrayList<Category>();
        Cursor result = mConnection.rawQuery("Select id, tipo from categoria",null);
        if(result.getCount()>0){
            result.moveToFirst();
            do{
                Category cat = new Category();
                cat.setId(result.getInt(result.getColumnIndexOrThrow("id")));
                cat.setTipo(result.getString(result.getColumnIndexOrThrow("tipo")));
                categories.add(cat);
                Log.d("CategoryDAO","Listando: "+cat.getId()+" - "+cat.getTipo());
            }while(result.moveToNext());
            result.close();
        }
        return categories;
    }


    public Category getCategory(int id){
        Category cat = new Category();
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        Cursor result = mConnection.rawQuery("Select * from categoria where id=?",params);
        if(result.getCount()>0){
            result.moveToFirst();
            cat.setId(result.getInt(result.getColumnIndexOrThrow("id")));
            cat.setTipo(result.getString(result.getColumnIndexOrThrow("tipo")));
            result.close();
            return cat;
        }

        return null;

    }



}



