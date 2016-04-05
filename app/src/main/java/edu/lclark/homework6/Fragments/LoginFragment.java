package edu.lclark.homework6.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.lclark.homework6.R;
import edu.lclark.homework6.SQLite.MapSQLiteHelper;
import edu.lclark.homework6.SQLite.User;

public class LoginFragment extends Fragment {

    @Bind(R.id.login_main_username_editText)
    EditText mUserEditText;

    @Bind(R.id.fragment_add_user_button)
    Button mAddButton;

    @Bind(R.id.fragment_login_button)
    Button mLoginButton;

    private MapSQLiteHelper mapSQLiteHelper;

    public interface UserCreatedListener{
        void onUserCreated(User user);
    }

    private UserCreatedListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);

        //getActivity().setTitle(getActivity().getString(R.string.sign_in));

        return rootView;
    }

    @OnClick(R.id.fragment_login_button)
    void searchUser(User user){
        String user1=mUserEditText.getText().toString().trim();
        if(user1.equals("")){
            Toast toast=Toast.makeText(getActivity(),R.string.no_entry,Toast.LENGTH_SHORT);
            toast.show();
        }else if(user.equals("tomas")) { // TODO: username is not found in database
            Toast toast = Toast.makeText(getActivity(), R.string.user_not_found, Toast.LENGTH_LONG)
                    toast.show();

        new User(user);
        launchMap();
    }

    @OnClick(R.id.fragment_add_user_button)
    void addUser(){ //User user
            String user2 = mUserEditText.getText().toString().trim();
            if (user1.equals("")) {
                Toast toast = Toast.makeText(getActivity(), R.string.no_entry, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                launchMap();
            }
        }



    public void launchMap(){//User user
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, new MapFragment());
        transaction.commit();
    }

}
