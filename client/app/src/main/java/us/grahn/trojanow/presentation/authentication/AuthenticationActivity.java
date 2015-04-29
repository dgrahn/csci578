package us.grahn.trojanow.presentation.authentication;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import us.grahn.trojanow.AuthenticationService;
import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.logic.AuthenticationManager;

/**
 * An interface which allows the user to authenticate
 *
 * @us.grahn.class
 * @us.grahn.component
 * @us.grahn.tier      Presentation
 */
public class AuthenticationActivity extends AccountAuthenticatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


    }

    public String getUsername() {
        return ((EditText) findViewById(R.id.username)).getText().toString();
    }

    public String getPassword() {
        return ((EditText) findViewById(R.id.password)).getText().toString();
    }

    public void authenticate(View view) {

        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(final Void... params) {
                return AuthenticationManager.I.login(getUsername(), getPassword());
            }

            @Override
            protected void onPostExecute(Result result) {
                finishLogin(result);
            }

        }.execute();
    }

    public void signup(View view) {

    }

    private void finishLogin(Result result) {

        if(result.getCode() == Result.LOGIN_SUCCESS) {
            final AccountManager manager = AccountManager.get(this);
            Account account = new Account(getUsername(), "us.grahn.trojanow");
            manager.addAccountExplicitly(account, getPassword(), null);
            manager.setAuthToken(account, AuthenticationService.AUTH_TYPE, result.getMessage());
            finish();
        } else if(result.getCode() == Result.LOGIN_FAIL) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.failed_login_message);
            builder.setTitle(R.string.failed_login_title);
            builder.setPositiveButton(R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
