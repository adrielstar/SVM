package com.freedom_mobile.smartvending.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom_mobile.smartvending.R;

import butterknife.Bind;

public class AddMoneyActivity extends AppCompatActivity {

    @Bind(R.id.radioGroup) RadioGroup mRadioGroup;
//    @Bind(R.id.btnAddMoney) Button mButtonSbm;
    private  static Button mButtonSbm;
    private  static RadioButton mRadioButton;
    private  static TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        onClickListenerButton();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickListenerButton(){
        mButtonSbm = (Button) findViewById(R.id.btnAddMoney);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mTextview = (TextView) findViewById(R.id.textViewAmmountMoney);

        mButtonSbm.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int selected_id = mRadioGroup.getCheckedRadioButtonId();
                mRadioButton = (RadioButton)findViewById(selected_id);
                Toast.makeText(AddMoneyActivity.this, mRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
