package com.freedom_mobile.smartvending.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom_mobile.smartvending.R;
import com.freedom_mobile.smartvending.model.Product;

import java.io.OutputStream;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.detail_product_poster) ImageView mDetailProductPoster;
    @Bind(R.id.product_price_label) TextView mProductPriceLabel;
    @Bind(R.id.product_description) TextView mProductDescriptionLabel;
    @Bind(R.id.product_buy_button) Button mBtnBuyProduct;

    //Blue
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "98:76:B6:00:64:A0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Product product = Product.productHashMap.get(id);

        String productTitle = product.getTitle();
        String productPoster = product.getImage();
        double productPrice = product.getPrice();
        String productDescription = product.getDescription();

        mBtnBuyProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();

                if(myBluetooth == null)
                {
                    //Show a mensag. that the device has no bluetooth adapter
                    Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

                    //finish apk
                    finish();
                }
                else if(!myBluetooth.isEnabled())
                {
                    //Ask to the user turn the bluetooth on
                    Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnBTon,1);
                }
                turnOnRelay();
            }
        });

        CollapsingToolbarLayout appBarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(productTitle);
        }

        Uri imagePath = Uri.parse(
                "android.resource://com.freedom_mobile.smartvending/drawable/"
                        + productPoster);

        mDetailProductPoster.setImageURI(imagePath);
        mProductPriceLabel.setText(String.format("Price: %s", String.valueOf(productPrice)));
        mProductDescriptionLabel.setText(productDescription);
    }



    // start Blue


    // Turn relay on
    private void turnOnRelay()
    {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(getApplicationContext(), "product id:" + id , Toast.LENGTH_LONG).show();

    }
}
