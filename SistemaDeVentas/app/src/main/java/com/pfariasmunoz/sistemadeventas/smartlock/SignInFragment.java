package com.pfariasmunoz.sistemadeventas.smartlock;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.credentials.Credential;
import com.pfariasmunoz.sistemadeventas.R;
import com.pfariasmunoz.sistemadeventas.util.CodelabUtil;

public class SignInFragment extends Fragment {

    private static final String TAG = "SignInFragment";
    private TextInputLayout mUsernameTextInputLayout;
    private EditText mUsernameEditText;
    private TextInputLayout mPasswordTextInputLayout;
    private EditText mPasswordEditText;
    private Button mSignInButton;
    private ProgressBar mSignInProgressBar;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_sign_in, container, false);
        mUsernameTextInputLayout = (TextInputLayout) view.findViewById(R.id.usernameTextInputLayout);
        mPasswordTextInputLayout = (TextInputLayout) view.findViewById(R.id.passwordTextInputLayout);

        mUsernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
        mUsernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateUsernameLayouts(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        mPasswordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validatePasswordLayouts(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        mSignInButton = (Button) view.findViewById(R.id.signInButton);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                setSignEnabled(false);
                String username = mUsernameTextInputLayout.getEditText().getText().toString();
                String password = mPasswordTextInputLayout.getEditText().getText().toString();

                /** When a valid credential is found initiate a call to save it.
                 * Replace the current if/else block in the Sign In button click listener.
                 * Check the credential now with the code below in SignInFragment. */
                // DONE Replace the current if/else block in the Sign In button click listener.
                Credential credential = new Credential.Builder(username)
                        .setPassword(password)
                        .build();
                if (CodelabUtil.isValidCredential(credential)) {
                    ((SignInActivity) getActivity()).saveCredential(credential);
                } else {
                    Log.d(TAG, "Credentials are invalid. Username or password are " +
                            "incorrect.");
                    Toast.makeText(view.getContext(), R.string.invalid_creds_toast_msg,
                            Toast.LENGTH_SHORT).show();
                    setSignEnabled(true);
                }
            }
        });

        Button clearButton = (Button) view.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsernameTextInputLayout.getEditText().setText("");
                mPasswordTextInputLayout.getEditText().setText("");
            }
        });

        mSignInProgressBar = (ProgressBar) view.findViewById(R.id.signInProgress);
        mSignInProgressBar.setVisibility(ProgressBar.INVISIBLE);

        return view;
    }

    public void onResume() {
        super.onResume();
        if (((SignInActivity) getActivity()).isResolving() || ((SignInActivity) getActivity()).isRequesting()) {
            setSignEnabled(false);
        } else {
            setSignEnabled(true);
        }
    }

    /**
     * Enable or disable Sign In form.
     *
     * @param enable Enable form when true, disable when false.
     */
    protected void setSignEnabled(boolean enable) {
        mSignInButton.setEnabled(enable);
        mUsernameEditText.setEnabled(enable);
        mPasswordEditText.setEnabled(enable);
        if (!enable) {
            mSignInProgressBar.setVisibility(ProgressBar.VISIBLE);
        } else {
            mSignInProgressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }

    /**
     * The following validation methods are only for the purpose of the code lab. In a production application
     * you should not indicate to the user when their username or password is incorrect until they submit.
     */
    private void validateUsernameLayouts(CharSequence charSequence) {
        if (!CodelabUtil.isValidUsernameSoFar(charSequence.toString())) {
            mUsernameTextInputLayout.setError(getString(R.string.invalid_username_error_msg));
        } else {
            mUsernameTextInputLayout.setError(null);
        }
    }

    private void validatePasswordLayouts(CharSequence charSequence) {
        String currentUsername = mUsernameEditText.getText().toString();
        if (!CodelabUtil.isValidPasswordSoFar(currentUsername, charSequence.toString())) {
            mPasswordTextInputLayout.setError("invalid password");
        } else {
            mPasswordTextInputLayout.setError(null);
        }
    }
}
