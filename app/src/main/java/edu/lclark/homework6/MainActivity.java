package edu.lclark.homework6;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.google.android.gms.maps.model.LatLng;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.homework6.Fragments.AddPinDialogFragment;
import edu.lclark.homework6.Fragments.LoginFragment;
import edu.lclark.homework6.Fragments.MapFragment;
import edu.lclark.homework6.SQLite.MapSQLiteHelper;
import edu.lclark.homework6.SQLite.Pins;
import edu.lclark.homework6.SQLite.User;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class MainActivity extends AppCompatActivity implements LoginFragment.UserCreatedListener, LoginFragment.UserLoginListener, AddPinDialogFragment.PinCreatedListener {
    private static final String TAG = "UserNotFound";
    @Bind(R.id.activity_main_framelayout)
    FrameLayout frameLayout;

    private User mUser;
    private MapSQLiteHelper mapSQLiteHelper;
    private LatLng place;



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


    @Override
    public void onLogin(User user){
       /*User foundUser= mapSQLiteHelper.checkUser(user.getUser());

        if (foundUser == null) {
           Log.d(TAG, "User not found");
        } else {*/
            mapSQLiteHelper.insertUser(user);
            /*foundUser = mapSQLiteHelper.checkUser(user.getUser());
            Log.d(TAG, foundUser.toString());
        }
        mUser=foundUser;*/
    }

    @Override
    public void onAdd(User user){
        mapSQLiteHelper.addOrUpdateUser(user);
    }



    @Override
    public void onLocationCreated(Pins pin){
        pin.setLatitude(place.latitude);
        pin.setLongitude(place.longitude);
        pin.setUserID(mUser.getmID());
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_framelayout);
        mapFragment.savedPins(pin);
        mapSQLiteHelper.insertPin(pin);

    }

}
