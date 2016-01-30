package com.freedom_mobile.smartvending.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.freedom_mobile.smartvending.R;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    @Bind(R.id.amountValue) EditText mAmountValue;
    @Bind(R.id.amountMoney) TextView mAmountMoney;
    @Bind(R.id.btnAddMoney) Button mBtnAddMoney;

    public AccountFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        mBtnAddMoney  = (Button) rootView.findViewById(R.id.btnAddMoney); // you have to use rootview object..
        mAmountMoney  = (TextView) rootView.findViewById(R.id.amountMoney);
        mAmountValue  = (EditText) rootView.findViewById(R.id.amountValue);

        mBtnAddMoney.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                mAmountMoney.setText(mAmountValue.getText().toString());
            }
        });

        return rootView;
    }
}