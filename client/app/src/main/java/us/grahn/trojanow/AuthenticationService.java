package us.grahn.trojanow;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import us.grahn.trojanow.presentation.authentication.AuthenticationActivity;

/**
 * Created by Dan on 4/28/2015.
 */
public class AuthenticationService extends Service {

    public static final String AUTH_TYPE = "us.grahn.trojanow";

    @Override
    public IBinder onBind(final Intent intent) {
        return new Authenticator(this).getIBinder();
    }

    public static class Authenticator extends AbstractAccountAuthenticator {

        private Context context;

        public Authenticator(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public Bundle editProperties(final AccountAuthenticatorResponse response, final String accountType) {
            return null;
        }

        @Override
        public Bundle addAccount(final AccountAuthenticatorResponse response, final String accountType, final String authTokenType, final String[] requiredFeatures, final Bundle options) throws NetworkErrorException {

            final Intent intent = new Intent(context, AuthenticationActivity.class);

            final Bundle bundle = new Bundle();
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
            return bundle;
        }

        @Override
        public Bundle confirmCredentials(final AccountAuthenticatorResponse response, final Account account, final Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(final AccountAuthenticatorResponse response, final Account account, final String authTokenType, final Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public String getAuthTokenLabel(final String authTokenType) {
            return null;
        }

        @Override
        public Bundle updateCredentials(final AccountAuthenticatorResponse response, final Account account, final String authTokenType, final Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle hasFeatures(final AccountAuthenticatorResponse response, final Account account, final String[] features) throws NetworkErrorException {
            return null;
        }
    }
}
