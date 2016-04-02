package edu.lclark.homework6.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.homework6.R;
import edu.lclark.homework6.SQLite.User;

public class LoginFragment extends Fragment {

    @Bind(R.id.login_main_username_editText)
    EditText mUserEditText;

    @Bind(R.id.fragment_main_add_user_button)
    Button mAddButton;

    @Bind(R.id.fragment_main_login_button)
    Button mLoginButton;

    public interface UserCreatedListener{
        void onUserCreated(User user);
    }

    private UserCreatedListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);

        getActivity().setTitle(getActivity().getString(R.string.sign_in));

        return rootView;
    }


}
