package us.grahn.trojanow.presentation.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.data.User;
import us.grahn.trojanow.logic.AuthenticationManager;
import us.grahn.trojanow.logic.Utilities;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    private String getEditText(int resId) {
        return ((EditText) findViewById(resId)).getText().toString();
    }

    public void signup(View view) {

        // Get values
        final String email = getEditText(R.id.signup_email);
        final String name = getEditText(R.id.signup_name);
        final String password = getEditText(R.id.signup_password);
        final String password_confirm = getEditText(R.id.signup_password_confirm);

        // Test values
        if(!name.matches("\\w+\\s\\w+")) {
            Utilities.showDialog(this, R.string.invalid_name_title, R.string.invalid_name_message);
            return;
        } else if(email.isEmpty()) {
            Utilities.showDialog(this, R.string.invalid_email_title, R.string.invalid_email_message);
            return;
        } else if(password.isEmpty()) {
            Utilities.showDialog(this, R.string.missing_password_title, R.string.missing_password_message);
            return;
        } else if(!password.equals(password_confirm)) {
            Utilities.showDialog(this, R.string.nonmatch_password_title, R.string.nonmatch_password_title);
            return;
        }

        // Build User
        final User user = new User();
        user.setGivenName(name.split("\\s")[0]);
        user.setSurname(name.split("\\s")[1]);
        user.setEmail(email);
        user.setPassword(password);

        // Try to signup
        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(final Void... params) {
                return AuthenticationManager.I.signup(user);
            }

            @Override
            protected void onPostExecute(Result result) {
                finishSignup(result, user);
            }
        }.execute();
    }

    private void finishSignup(Result result, User user) {

        if(result.getCode() == Result.SIGNUP_SUCCESS) {
            // On success, return login info
            Intent data = new Intent();
            data.putExtra("username", user.getEmail());
            data.putExtra("password", user.getPassword());
            setResult(RESULT_OK, data);
            finish();
        } else {
            // On failure, show the dialog
            Utilities.showDialog(this, result);
        }
    }
}
