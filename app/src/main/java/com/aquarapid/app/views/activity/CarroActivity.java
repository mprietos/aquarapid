package com.aquarapid.app.views.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aquarapid.app.App;
import com.aquarapid.app.R;
import com.aquarapid.app.adapters.ItemsCartRecyclerViewAdapter;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarroActivity extends BaseActivity implements ProductListCallBack {


    private static  double ivaW = 1.10;
    private static  double ivaT = 1.21;
    private  static double noT = 100; //  a partir de aqui no se paga transporte
    private static double costeTrans = 10; // El transporte vale 10
    private SessionHelper mSession;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @BindView(R.id.comprar)
    Button mComprar;

    @BindView(R.id.totalitems)
    TextView totalitems;
    @BindView(R.id.totaltrans)
    TextView totaltrans;
    @BindView(R.id.total)
    TextView total;
    private ProgressDialog progressDialog;

    private ItemsCartRecyclerViewAdapter adapter;
    private List<ItemsCart> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        super.initToolbar(true);
        ButterKnife.bind(this);

        mSession = new SessionHelper(this);
        if (!this.mSession.isLoggedIn()){
            this.finish();
        }else{

            loadProducts();

        }

        mComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaoSession daoSession = ((App) getApplication()).getDaoSession();
                ItemsCartDao itemsCartDao = daoSession.getItemsCartDao();
                itemsCartDao.queryBuilder().where(ItemsCartDao.Properties.Cif.eq(mSession.getCIF())).buildDelete().executeDeleteWithoutDetachingEntities();

                //Toast.makeText(CarroActivity.this, "Compra realizada. Recibiras un email", Toast.LENGTH_LONG).show();
                // custom dialog
                final Dialog dialog = new Dialog(CarroActivity.this);
                dialog.setContentView(R.layout.pedidook);
                dialog.setTitle("Pedido");


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        CarroActivity.this.finish();
                    }
                });

                dialog.show();
            }
        });


    }

    private void loadProducts() {
        if (this.mSession.isLoggedIn()) {

            progressDialog = new ProgressDialog(this);
            progressDialog.show();

            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            ItemsCartDao itemsCartDao = daoSession.getItemsCartDao();
            productList = itemsCartDao.queryBuilder().where(ItemsCartDao.Properties.Cif.eq(mSession.getCIF())).list();

            if (productList.size() == 0){
                total.setVisibility(View.GONE);
                totalitems.setVisibility(View.GONE);
                totaltrans.setVisibility(View.GONE);
                mComprar.setVisibility(View.GONE);

            }else{
                total.setVisibility(View.VISIBLE);
                totalitems.setVisibility(View.VISIBLE);
                totaltrans.setVisibility(View.VISIBLE);
                mComprar.setVisibility(View.VISIBLE);
            }
            double totalC = 0;
            double totalT = 0;
            for (ItemsCart product: productList) {
                totalC += totalC + (product.getPrice() * product.getQty());
            }

            totalitems.setText("Total agua: " + String.format("%.2f", totalC)  + "€ + 10% IVA: " +  String.format("%.2f", (totalC * this.ivaW) ) + "€" );
            if (totalC > this.noT){
                totalT = 0;
                totaltrans.setText("Transporte: 0");
            }else{
                totalT = costeTrans * this.ivaT;
                totaltrans.setText("Total transporte 10€ + 21% IVA: " + String.format("%.2f", totalT) + "€" );
            }
            totalC = totalC + this.ivaW;
            total.setText("Total: " + String.format("%.2f", (totalC+totalT)) + "€" );


            adapter = new ItemsCartRecyclerViewAdapter(getBaseContext(), productList, this, false);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(adapter);

            progressDialog.dismiss();
        }

    }

    @Override
    public void onClickProduct(Product product) {

    }

    @Override
    public void addChart(Product product) {

    }
}
