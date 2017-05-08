package com.aquarapid.app.views.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aquarapid.app.App;
import com.aquarapid.app.R;
import com.aquarapid.app.dao.types.Customer;
import com.aquarapid.app.dao.types.CustomerDao;
import com.aquarapid.app.dao.types.DaoSession;
import com.aquarapid.app.dao.types.Product;
import com.aquarapid.app.dao.types.ProductDao;
import com.aquarapid.app.utils.ProgressDialog;
import com.aquarapid.app.utils.Utils;
import com.aquarapid.app.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends BaseActivity {

    @BindView(R.id.code)
    EditText mCode;

    @BindView(R.id.desc)
    EditText mDesc;

    @BindView(R.id.image)
    EditText mImage;

    @BindView(R.id.price)
    EditText mPrice;

    @BindView(R.id.save)
    Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        super.initToolbar(true);
        ButterKnife.bind(this);


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSave(mCode.getText().toString(), mDesc.getText().toString(), mImage.getText().toString(), mPrice.getText().toString());
            }
        });
    }

    private void doSave(String code, String desc, String image, String price) {

        if (code.length() >0 && desc.length() > 0  && price.length() > 0){
            Product product = new Product();
            product.setCode(code);
            product.setDesc(desc);
            product.setFoto(image);
            product.setPrice(Double.parseDouble(price.replace(",",".")));

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.show();
            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            ProductDao mDao = daoSession.getProductDao();

            mDao.save(product);
            progressDialog.dismiss();

            this.finish();

        }else{
            Toast.makeText(this, "Todos los campos obligatorios", Toast.LENGTH_LONG).show();
        }
    }

}
