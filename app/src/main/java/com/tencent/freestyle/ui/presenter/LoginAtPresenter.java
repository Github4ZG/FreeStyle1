package com.tencent.freestyle.ui.presenter;

import com.tencent.freestyle.ui.base.BaseActivity;
import com.tencent.freestyle.ui.base.BasePresenter;
import com.tencent.freestyle.ui.view.ILoginAtView;
import com.tencent.freestyle.ui.activity.MainActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Greyzhou on 2017/6/29.
 */

public class LoginAtPresenter extends BasePresenter<ILoginAtView> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    public LoginAtPresenter(ILoginAtView context) {
        super(context);
    }

    public void login(String email,String password){
        Observable.just(new UserInput(email,password))
                .map(userInput -> {
                    try {
                        // Simulate network access.
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        return false;
                    }

                    for (String credential : DUMMY_CREDENTIALS) {
                        String[] pieces = credential.split(":");
                        if (pieces[0].equals(userInput.getEmail())) {
                            // Account exists, return true if the password matches.
                            return pieces[1].equals(userInput.getPassword());
                        }
                    }
                    return true;
                }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    getView().showProgress(false);

                    if (success) {
                        mContext.jumpToActivityAndClearTask(MainActivity.class);
                        mContext.finish();
                    } else {
                        getView().handlePswError();
                    }
                });
    }
    private static class UserInput{
        private String mEmail;
        private String mPassword;

        public String getEmail() {
            return mEmail;
        }

        public String getPassword() {
            return mPassword;
        }

        public UserInput(String email,String password){
            mEmail = email;
            mPassword = password;
        }
    }
}
