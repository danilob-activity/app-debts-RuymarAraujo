package com.example.danilo.appdebts.classes;

/**
 * Created by aluno on 27/06/19.
 */

public class Category {

    private long mId;
    public String mTipo;

    public Category(String Tipo) {
    }


    public String getTipo(){
        return mTipo;
    }

    public void setTipo(String tipo) {

        mTipo = tipo;
    }


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }


}
