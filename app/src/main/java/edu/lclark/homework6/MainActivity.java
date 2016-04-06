package edu.lclark.homework6;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.homework6.Fragments.LoginFragment;
import edu.lclark.homework6.SQLite.MapSQLiteHelper;
import edu.lclark.homework6.SQLite.Pins;
import edu.lclark.homework6.SQLite.User;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "UserNotFound";
    @Bind(R.id.activity_main_framelayout)
    FrameLayout frameLayout;

    private User mUser;
    private MapSQLiteHelper mapSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        mapSQLiteHelper = MapSQLiteHelper.getInstance(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, new LoginFragment());
        transaction.commit();
    }


    public User onUserCreated(User user){
        User foundUser = mapSQLiteHelper.checkUser(user.getUser());

        if (foundUser == null) {
            Log.d(TAG, "User not found");
            mapSQLiteHelper.insertUser(user);
            foundUser = mapSQLiteHelper.checkUser(user.getUser());
        } else {
            Log.d(TAG, foundUser.toString());
        }
        return foundUser;
    }

    @Override
    public void onPinCreated(Pins pin){
        Pins mPin=mapSQLiteHelper.getAllPins();

    }

}
