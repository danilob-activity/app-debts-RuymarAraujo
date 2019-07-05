package com.example.danilo.appdebts.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.danilo.appdebts.R;
import com.example.danilo.appdebts.classes.Debts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 03/07/19.
 */

public class DebtsAdapter extends RecyclerView.Adapter<DebtsAdapter.ViewHolderDebts> {
    private List<Debts> mData;
    private List<ViewHolderDebts> mDataViews = new ArrayList<ViewHolderDebts>();
    private int selectedItem = -1; //indice do ultimo viewHolder selecionado
    private int actualItem = -1; // indice do atual viewholder


    private int mDescription;
    private int mButtonVenc;
    private int mButtonAtualiza;
    private int mButtonDelete;


    public DebtsAdapter(List<Debts> data) {
        mData = data;
    }
    @NonNull
    @Override
    public DebtsAdapter.ViewHolderDebts onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_view_debts, parent, false);
        ViewHolderDebts holderDebts = new ViewHolderDebts(view);
        mDataViews
        return holderDebts;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDebts holder, int position) {
        if ((mData != null) && (mData.size() > 0)) {
            Debts debt = mData.get(position);
            holder.mDescription.setText(debt.getDescricao());
        }
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolderDebts extends RecyclerView.ViewHolder {
        public TextView mDescription;
        public ImageButton mButtonVenc;
        public ImageButton mButtonAtualiza;
        public ImageButton mButtonDelete;
        public TextView mPayment;
        public TextView mPay;


        public ViewHolderDebts(View itemView) {
            super(itemView);
            mDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }
}