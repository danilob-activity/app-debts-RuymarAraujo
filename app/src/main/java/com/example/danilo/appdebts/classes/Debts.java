package com.example.danilo.appdebts.classes;

/**
 * Created by aluno on 27/06/19.
 */

public class Debts {
    private long mId;
    private Category mCategoria;
    private float mValor;
    private String mDescricao;
    private String mDataVencimento;
    private String mDataPagamento;

    public Debts() {

    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public Category getCategoria() {
        return mCategoria;
    }

    public void setCategoria(Category categoria) {
        mCategoria = categoria;
    }

    public float getValor() {
        return mValor;
    }

    public void setValor(float valor) {
        mValor = valor;
    }

    public String getDescricao() {
        return mDescricao;
    }

    public void setDescricao(String descricao) {
        mDescricao = descricao;
    }

    public String getDataVencimento() {
        return mDataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        mDataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return mDataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        mDataPagamento = dataPagamento;
    }

    public Debts(Category categoria, String descricao, float val, String dataVenc, String dataPg){
        this.mCategoria = categoria;
        this.mDescricao = descricao;
        this.mValor = val;
        this.mDataVencimento = dataVenc;
        this.mDataPagamento = dataPg;
    }
}
