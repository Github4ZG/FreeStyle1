package com.tencent.freestyle.ui.view;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/**
 * Created by Greyzhou on 2017/6/29.
 */

public interface ILoginAtView {
    AutoCompleteTextView getEmailView();
    EditText getPasswordView();
    void showProgress(boolean show);
    void handlePswError();
}
