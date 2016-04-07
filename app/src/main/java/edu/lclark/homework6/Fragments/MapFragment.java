package edu.lclark.homework6.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.homework6.R;
import edu.lclark.homework6.SQLite.Pins;
import edu.lclark.homework6.SQLite.User;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,OnMapClickListener {
    @Bind(R.id.mapView)
    MapView mMapView;

    private GoogleMap mMap;
    private AddPinDialogFragment mDialogFragment;
    private LatLng position;
    private User mUser;
    public static final String ARG_USER = "MapFragment.ArgUser";
    private String mUserLogin="maia";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_activity, container, false);
        ButterKnife.bind(this, rootView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        return rootView;

    }
    public static Fragment newInstance(User user) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng point = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(point));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng point) {
        mMap.addMarker(new MarkerOptions()
                .position(point)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("Title").snippet("Description")
                .draggable(true));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 3));

        mDialogFragment = new AddPinDialogFragment();
        mDialogFragment.show(getFragmentManager(), "dialog");
    }
public void savedPins(Pins pin) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(pin.getLatitude(), pin.getLongitude()))
                .draggable(false)
                .title(pin.getTitle())
                .snippet(pin.getDescription()));
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}