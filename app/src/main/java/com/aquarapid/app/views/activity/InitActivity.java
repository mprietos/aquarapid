package com.aquarapid.app.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aquarapid.app.App;
import com.aquarapid.app.R;
import com.aquarapid.app.adapters.ItemsRecyclerViewAdapter;
import com.aquarapid.app.callbacks.ProductListCallBack;
import com.aquarapid.app.dao.types.DaoSession;
import com.aquarapid.app.dao.types.ItemsCart;
import com.aquarapid.app.dao.types.ItemsCartDao;
import com.aquarapid.app.dao.types.Product;
import com.aquarapid.app.dao.types.ProductDao;
import com.aquarapid.app.utils.ProgressDialog;
import com.aquarapid.app.utils.SessionHelper;
import com.aquarapid.app.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InitActivity extends BaseActivity implements ProductListCallBack {

    private SessionHelper mSession;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.dlogout)
    Button mLogout;
    @BindView(R.id.cart)
    Button mCarro;
    private ProgressDialog progressDialog;

    private ItemsRecyclerViewAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mSession = new SessionHelper(getBaseContext());

        if (!this.mSession.isLoggedIn()){
            startActivityForResult(new Intent(this, LoginActivity.class), 100);
        }else{

            loadProducts();

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(InitActivity.this, AdminActivity.class), 300);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSession.logoutUser();
                startActivityForResult(new Intent(InitActivity.this, LoginActivity.class), 100);

            }
        });

        mCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(InitActivity.this, CarroActivity.class), 200);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.loadProducts();
    }

    private void loadProducts() {
        if (this.mSession.isLoggedIn()) {
            if (this.mSession.isAdmin()){
                this.fab.setVisibility(View.VISIBLE);
            }else{
                this.fab.setVisibility(View.GONE);
            }
            progressDialog = new ProgressDialog(this);
            progressDialog.show();

            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            ProductDao productDao = daoSession.getProductDao();
            productList = productDao.queryBuilder().orderAsc(ProductDao.Properties.Code).build().list();
            adapter = new ItemsRecyclerViewAdapter(getBaseContext(), productList, this, false);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(adapter);

            progressDialog.dismiss();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 1
        getMenuInflater().inflate(R.menu.menuinit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
        }
        if (item.getItemId() == R.id.MnuOpc1){
            startActivityForResult(new Intent(InitActivity.this, AcercaActivity.class), 600);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickProduct(Product product) {

    }

    @Override
    public void addChart(Product product) {
        if (this.mSession.isAdmin()){
            Toast.makeText(this, "EL admin no puede comprar", Toast.LENGTH_LONG).show();
        }else {
            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            ItemsCartDao mDao = daoSession.getItemsCartDao();

            ItemsCart itemsCart = new ItemsCart(mSession.getCIF(), product.getCode(), product.getDesc(), "aaa", product.getPrice());
            mDao.save(itemsCart);
            Toast.makeText(this, "Producto añadido", Toast.LENGTH_LONG).show();
        }



    }
}
