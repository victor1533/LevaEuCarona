package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.orochi.utfpr.levaeu.Callback;
import com.orochi.utfpr.levaeu.Campus;
import com.orochi.utfpr.levaeu.ConexaoUTFPR.UTFPR.PuxarDadosAluno;
import com.orochi.utfpr.levaeu.Pessoa;
import com.orochi.utfpr.levaeu.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.login);
        mPasswordView = (EditText) findViewById(R.id.senha);
        mEmailView.setText("1602632");
        mPasswordView.setText("purolestat123");

        Button logar = (Button) findViewById(R.id.acessarBotao);
        logar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }





    /**
     * Callback received when a permissions request has been completed.
     */

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String login = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError("Sua senha possui poucos digitos");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(login)) {
            mEmailView.setError("Preencha esta merda.");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            PuxarDadosAluno pDados = new PuxarDadosAluno(LoginActivity.this, new Callback(){
                public void run(Object aluno){

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("pessoa", (Pessoa) aluno);
                    startActivity(intent);
                }}, Campus.getCampoMourao());
            pDados.execute(login, password);

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }





    /**
     * Use an AsyncTask to fetch the user's email addresses on a background thread, and update
     * the email text field with results on the main UI thread.
     */
}

