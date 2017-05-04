package com.aquarapid.app.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aquarapid.app.App;
import com.aquarapid.app.R;
import com.aquarapid.app.dao.types.Customer;
import com.aquarapid.app.dao.types.CustomerDao;
import com.aquarapid.app.dao.types.DaoSession;
import com.aquarapid.app.utils.ProgressDialog;
import com.aquarapid.app.utils.SessionHelper;
import com.aquarapid.app.utils.Utils;
import com.aquarapid.app.views.BaseActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.pwd) EditText mPwd;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.registro)
    Button mRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.initToolbar(true);
        ButterKnife.bind(this);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pwd = mPwd.getText().toString();

                boolean isOk = true;
                if (email.equals("admin") && pwd.equals("admin")) {
                    isOk = true;
                }else{
                    if (pwd.length() == 0) {
                        isOk = false;
                        mPwd.setError(getString(R.string.campoobliga));
                    } else {
                        if (email.length() == 0) {
                            isOk = false;
                            mEmail.setError(getString(R.string.campoobliga));
                        } else {
                            if (!Utils.isEmailValid(email)) {
                                isOk = false;
                                mEmail.setError(getString(R.string.emailmal));
                            }
                        }
                    }
                }
                if (isOk)
                    doLogin(email, pwd);

            }
        });

        mRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 100);
            }
        });


    }

    private void doLogin(String email, String pwd) {
        boolean isAdmin = false;
        boolean isLogin = false;
        String cif = "";


        if (email.equals("admin") && pwd.equals("admin")){
            // IS admin
            createSessionAndClose(email, cif, true);
        }else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.show();
            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            CustomerDao customerDao = daoSession.getCustomerDao();


            List<Customer> clis = customerDao.queryBuilder()
                    .where(CustomerDao.Properties.Email.eq(email), CustomerDao.Properties.Pwd.eq(Utils.md5(pwd)))
                    .list();
            if (clis.size() == 0) {
                isLogin = false;
            } else {
                isLogin = true;
                cif = clis.get(0).getCif();
            }
            progressDialog.dismiss();
            if (!isLogin) {
                Toast.makeText(getApplicationContext(), getString(R.string.errorLogin), Toast.LENGTH_LONG).show();
            } else {
                createSessionAndClose(email, cif, isAdmin);

            }
        }
    }

    private void createSessionAndClose(String email, String cif, boolean isAdmin) {
        SessionHelper mSession = new SessionHelper(this);
        mSession.createUserSession(email, cif, isAdmin);
        setResult(RESULT_OK);
        this.finish();
    }


}
