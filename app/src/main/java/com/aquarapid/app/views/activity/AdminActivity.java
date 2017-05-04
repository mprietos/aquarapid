package com.aquarapid.app.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aquarapid.app.App;
import com.aquarapid.app.R;
import com.aquarapid.app.adapters.ItemsAdminRecyclerViewAdapter;
import com.aquarapid.app.adapters.ItemsRecyclerViewAdapter;
import com.aquarapid.app.callbacks.ProductAdminListCallBack;
import com.aquarapid.app.dao.types.DaoSession;
import com.aquarapid.app.dao.types.Product;
import com.aquarapid.app.dao.types.ProductDao;
import com.aquarapid.app.utils.ProgressDialog;
import com.aquarapid.app.utils.SessionHelper;
import com.aquarapid.app.views.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminActivity extends BaseActivity implements ProductAdminListCallBack {
    private SessionHelper mSession;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private ProgressDialog progressDialog;

    private ItemsAdminRecyclerViewAdapter adapter;
    private List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        super.initToolbar(true);
        ButterKnife.bind(this);

        mSession = new SessionHelper(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AdminActivity.this, ProductActivity.class),500);
            }
        });

        if (!this.mSession.isAdmin()){
            this.finish();
        }else{

            loadProducts();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.loadProducts();
    }

    private void loadProducts() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        ProductDao productDao = daoSession.getProductDao();
        productList = productDao.queryBuilder().orderAsc(ProductDao.Properties.Code).build().list();
        adapter = new ItemsAdminRecyclerViewAdapter(getBaseContext(), productList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        progressDialog.dismiss();

    }


    @Override
    public void editProduct(Product product) {

    }

    @Override
    public void delProduct(Product product) {

    }
}
