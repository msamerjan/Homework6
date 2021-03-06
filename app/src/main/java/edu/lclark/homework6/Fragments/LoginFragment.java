package edu.lclark.homework6.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.lclark.homework6.R;
import edu.lclark.homework6.SQLite.User;

public class LoginFragment extends Fragment {

    public static final String APP_LOGO = "http://www.indiantrailslibrary.org/images/planet-earth.png";
    public static final String TAG="UserNotFound";

    @Bind(R.id.login_main_image)
    ImageView mImageView;

    @Bind(R.id.login_main_username_editText)
    EditText mUserEditText;

    @Bind(R.id.fragment_add_user_button)
    Button mAddButton;

    @Bind(R.id.fragment_login_button)
    Button mLoginButton;

    private User mUser;
    private LatLng position;
    private UserCreatedListener userCreated;
    private UserLoginListener loginCreated;

    public interface UserCreatedListener{
        void onAdd(User user);
    }

    public interface UserLoginListener {
        void onLogin(User user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);

        getActivity().setTitle(getActivity().getString(R.string.sign_in));
        Picasso.with(getActivity()).load(APP_LOGO).fit().centerInside().into(mImageView);

        userCreated = (UserCreatedListener) getActivity();
        loginCreated = (UserLoginListener) getActivity();

        return rootView;
    }

    @OnClick(R.id.fragment_login_button)
    public void searchForUser() {
        String user1 = mUserEditText.getText().toString().trim();
        if (user1.isEmpty()) {
            Toast toast = Toast.makeText(getActivity(), R.string.no_entry, Toast.LENGTH_SHORT);
            toast.show();
        } else{
            mUser = new User(user1);
            loginCreated.onLogin(mUser);
            launchMap(mUser);

        }

    }
    @OnClick(R.id.fragment_add_user_button)
        public void addNewUser(){
            String user2 = mUserEditText.getText().toString().trim();
            if (user2.isEmpty()) {
                Toast toast = Toast.makeText(getActivity(), R.string.no_entry, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                mUser = new User(user2);
                userCreated.onAdd(mUser);
                launchMap(mUser);
            }
        }



    public void launchMap(User user){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout,MapFragment.newInstance(user));
        transaction.commit();
    }

}
