package com.google.codelabs.mdc.java.shrine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_login_fragment, container, false);

        final TextInputLayout passwordInputLayout = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordInputEditText = view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordInputEditText.getText())) {
                    passwordInputLayout.setError(getString(R.string.shr_error_password));
                } else {
                    passwordInputLayout.setError(null);
                    ((NavigationHost) Objects.requireNonNull(getActivity())).navigateTo(new ProductGridFragment(), false);
                }
            }
        });

        passwordInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isPasswordValid(passwordInputEditText.getText())) {
                    passwordInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

}
