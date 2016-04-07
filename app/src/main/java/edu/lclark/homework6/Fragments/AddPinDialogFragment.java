package edu.lclark.homework6.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.homework6.R;
import edu.lclark.homework6.SQLite.Pins;

/**
 * Created by maiaphoebedylansamerjan on 3/31/16.
 */
public class AddPinDialogFragment extends DialogFragment {
    @Bind(R.id.location_title)
    EditText mLocationTitel;
    @Bind(R.id.location_description)
    EditText mLocationDescription;


    public interface PinCreatedListener {
        void onLocationCreated(Pins pin);
    }

    private PinCreatedListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mListener = (PinCreatedListener) getActivity();

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.pin_detail_dialogfragment, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .setTitle(getActivity().getString(R.string.new_pin))
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String titel = mLocationTitel.getText().toString().trim();
                                String description = mLocationDescription.getText().toString().trim();

                                Pins pin = new Pins(titel, description);
                                mListener.onLocationCreated(pin);

                            }
                        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        ButterKnife.bind(this, rootView);

        return dialog;
    }
}

