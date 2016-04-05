package edu.lclark.homework6;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.Bind;
import edu.lclark.homework6.Fragments.LoginFragment;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class MainActivity extends AppCompatActivity{
    @Bind(R.id.activity_main_framelayout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, new LoginFragment());
        transaction.commit();
    }

}
