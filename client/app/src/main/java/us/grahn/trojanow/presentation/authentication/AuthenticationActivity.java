package us.grahn.trojanow.presentation.authentication;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

    private final static int REQUEST_USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(AuthenticationManager.I.isLoggedIn()) {
           finish();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(REQUEST_USER == requestCode && RESULT_OK == resultCode) {
            final String username = data.getStringExtra("username");
            final String password = data.getStringExtra("password");

            Log.i("Login", "Username = " + username);
            login(username, password);
        }
    }

    public String getUsername() {
        return ((EditText) findViewById(R.id.username)).getText().toString();
    }

    public String getPassword() {
        return ((EditText) findViewById(R.id.password)).getText().toString();
    }

    public void authenticate(View view) {
        login(getUsername(), getPassword());
    }

    /**
     * Login with a username and password.
     *
     * @param username the username with which to login
     * @param password the password with which to login
     */
    private void login(final String username, final String password) {

        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(final Void... params) {
                return AuthenticationManager.I.login(username, password);
            }

            @Override
            protected void onPostExecute(Result result) {
                finishLogin(username, password, result);
            }

        }.execute();
    }

    /**
     * Start the signup activity.
     *
     * @param view the view which called this method
     */
    public void signup(View view) {
        startActivityForResult(new Intent(this, SignUpActivity.class), REQUEST_USER);
    }

    /**
     * Finish the login AsyncTask.
     *
     * @param username the username which attempted to login
     * @param password the password which attempted to login
     * @param result the result of the login
     */
    private void finishLogin(final String username, final String password, final Result result) {

        if(result.getCode() == Result.LOGIN_SUCCESS) {
            final AccountManager manager = AccountManager.get(this);
            Account account = new Account(username, "us.grahn.trojanow");
            manager.addAccountExplicitly(account, password, null);
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
