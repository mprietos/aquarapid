package com.aquarapid.app.views.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aquarapid.app.App;
import com.aquarapid.app.R;
import com.aquarapid.app.dao.types.Customer;
import com.aquarapid.app.dao.types.CustomerDao;
import com.aquarapid.app.dao.types.DaoSession;
import com.aquarapid.app.utils.ProgressDialog;
import com.aquarapid.app.utils.Utils;
import com.aquarapid.app.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.cif)
    EditText mCif;
    @BindView(R.id.nombre)
    EditText mNombre;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.pwd) EditText mPwd;
    @BindView(R.id.reppwd) EditText mRepPwd;
    @BindView(R.id.show)
    ImageView mShow;

    private boolean showing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        super.initToolbar(true);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cif, nombre, email, pwd, reppwd;
                cif = mCif.getText().toString();
                nombre = mNombre.getText().toString();
                email = mEmail.getText().toString();
                pwd = mPwd.getText().toString();
                reppwd = mRepPwd.getText().toString();
                if (cif.length() == 0){
                    mCif.setError(getString(R.string.campoobliga));
                }else{
                    if (nombre.length() == 0){
                        mNombre.setError(getString(R.string.campoobliga));
                    }else{
                        if (email.length() == 0){
                            mEmail.setError(getString(R.string.campoobliga));
                        }else{
                            if (!Utils.isEmailValid(email)){
                                mEmail.setError(getString(R.string.emailmal));
                            }else{
                                if (!pwd.equals(reppwd)){
                                    mPwd.setError(getString(R.string.pwdnomatch));
                                }else{
                                    doRegister(cif, nombre, email, pwd);
                                }
                            }
                        }
                    }
                }
            }
        });
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showing){
                    showing = false;
                    mShow.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye));
                    mPwd.setTransformationMethod(new PasswordTransformationMethod());
                    mRepPwd.setTransformationMethod(new PasswordTransformationMethod());
                }else{
                    showing = true;
                    mShow.setImageDrawable(getResources().getDrawable(R.drawable.ic_hide));
                    mPwd.setTransformationMethod(null);
                    mRepPwd.setTransformationMethod(null);
                }
            }
        });
    }

    private void doRegister(String cif, String nombre, String email, String pwd) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        CustomerDao customerDao = daoSession.getCustomerDao();

        Customer customer = new Customer(cif, nombre, email, Utils.md5(pwd));
        customerDao.insert(customer);
        progressDialog.dismiss();
        Toast.makeText(this, getString(R.string.registrook), Toast.LENGTH_LONG).show();
        this.finish();
    }

}
