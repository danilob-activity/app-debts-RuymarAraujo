package com.example.danilo.appdebts.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.danilo.appdebts.R;
import com.example.danilo.appdebts.classes.Debts;
import com.example.danilo.appdebts.dao.DebtsDAO;
import com.example.danilo.appdebts.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 03/07/19.
 */
//

public class DebtsAdapter extends RecyclerView.Adapter<DebtsAdapter.ViewHolderDebts> {
    private List<Debts> mData;

    public DebtsAdapter(List<Debts> data) {
        mData = data;
    }

    private List<ViewHolderDebts> mDataViews = new ArrayList<ViewHolderDebts>();
    private int selectedItem = -1; //índice do último viewholder selecionado
    private int actualItem = -1;   //índice do atual viewholder selecionado

    public DebtsAdapter(List<Debts> data) {
        mData = data;
    }

    private Context mContext;

    @NonNull
    @Override
    public DebtsAdapter.ViewHolderDebts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.list_view_debts, parent, false);

        ViewHolderDebts holderDebts = new ViewHolderDebts(view);


        return holderDebts;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDebts holder, int position) {
        if (mData != null && mData.size() > 0) {
            Debts debt = mData.get(position);
            holder.mDescription.setText(debt.getDescricao());
//            holder.mCategory.setText(debt.getCategoria().getTipo());
//            holder.mDataPay.setText(debt.getDataVencimento());
//            holder.mDataPayment.setText(debt.getDataPagamento());
//            if (debt.getDataVencimento().isEmpty()) {
//                holder.mTextPay.setVisibility(View.GONE);
//            } else {
//                holder.mTextPay.setVisibility(View.VISIBLE);
//            }


        }
    }

    //apagar a visibilidade do último item selecionado
    public void updateViewHolderLast() {
        ViewHolderDebts holder = mDataViews.get(selectedItem);
        holder.mButtonUpdate.setVisibility(View.GONE);
        holder.mButtonPay.setVisibility(View.GONE);
        holder.mButtonDelete.setVisibility(View.GONE);
        holder.mLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolderDebts extends RecyclerView.ViewHolder {
        public TextView mDescription;
        public TextView mCategory;
        public TextView mDataPay;
        public TextView mDataPayment;
        public TextView mTextPayment;
        public TextView mTextPay;
        public ImageButton mButtonUpdate;
        public ImageButton mButtonPay;
        public ImageButton mButtonDelete;
        public ConstraintLayout mLayout;


        public ViewHolderDebts(View itemView) {
            super(itemView);
            mDescription = itemView.findViewById(R.id.textViewDescription);
            mCategory = itemView.findViewById(R.id.textViewCategory);
            mDataPay = itemView.findViewById(R.id.textViewPay);
            mDataPayment = itemView.findViewById(R.id.textViewPayment);
            mButtonPay = itemView.findViewById(R.id.imageButtonPayment);
            mButtonUpdate = itemView.findViewById(R.id.imageButtonUpdate);
            mButtonDelete = itemView.findViewById(R.id.imageButtonDelete);
            mTextPay = itemView.findViewById(R.id.textViewPayString);
            mTextPayment = itemView.findViewById(R.id.textViewPaymentString);
            mLayout = itemView.findViewById(R.id.linearLayout);


            mButtonPay.setVisibility(View.GONE);
            mButtonUpdate.setVisibility(View.GONE);
            mButtonDelete.setVisibility(View.GONE);

            mDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actualItem = getLayoutPosition();
                    if (selectedItem != -1 && actualItem != selectedItem) {
                        updateViewHolderLast();
                    }
                    if (mButtonDelete.getVisibility() == View.GONE) {
                        mButtonPay.setVisibility(View.VISIBLE);
                        mButtonUpdate.setVisibility(View.VISIBLE);
                        mButtonDelete.setVisibility(View.VISIBLE);
                        mLayout.setBackgroundColor(mContext.getResources().getColor(R.color.selectedItem));

                    } else {
                        mButtonPay.setVisibility(View.GONE);
                        mButtonUpdate.setVisibility(View.GONE);
                        mButtonDelete.setVisibility(View.GONE);
                        mLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));

                    }
                    selectedItem = actualItem;
                }
            });

            mButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mData.size() > 0) {
                        Debts debt = mData.get(getLayoutPosition());
                        DataBaseHelper mDataHelper = new DataBaseHelper(mContext);
                        SQLiteDatabase mConection = mDataHelper.getWritableDatabase();
                        DebtsDAO debtDAO = new DebtsDAO(mConection);
                        debtDAO.remove(debt.getId());
                        mData.remove(getLayoutPosition());
                        notifyItemRemoved(getLayoutPosition());
                    }
                }
            });
        }
    }
}