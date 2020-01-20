package com.hypersoft.golfapp.ui.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.NearbyPermissions;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.MapsFragmentBinding;
import com.hypersoft.golfapp.ui.activity.ScoreCardDialog;
import com.hypersoft.golfapp.ui.communicator.FragmentReplaceCommunicator;
import com.hypersoft.golfapp.utils.LocationService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;


/**
 * Created by Apple on 4/12/2017.
 */
public class MapsFragmentFragment extends BaseFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private MapsFragmentBinding binding;
    private FragmentReplaceCommunicator fragmentReplaceCommunicator;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private PolylineOptions mPolylineOptions;
    private double lat;
    private double lon;

    LocationService mLocationService;
    int mapValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentReplaceCommunicator = (FragmentReplaceCommunicator) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.maps_fragment, container, false);
        binding.setMapFragment(this);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mapValue = bundle.getInt("value");
        }
        return binding.getRoot();
    }

    private MessageListener mMessageListenear = new MessageListener() {

        @Override
        public void onFound(Message message) {
            /*EddystoneUid eddystoneUid = new EddystoneUid(EddystoneUid.from(message).getHex());
            eddystoneUid.getHex();
            String hex = EddystoneUid.from(message).getHex();
            String instance = EddystoneUid.from(message).getInstance();
            String nameSpace = EddystoneUid.from(message).getNamespace();

            Log.d("TAG", "Hex = " + hex + "INSTANCE" + instance + "NAMESPACE" + nameSpace);
            Log.d("TAG", "NAMESPACE + " + message.getNamespace());*/
            String beaconMessage = new String(message.getContent());
            try {
                JSONObject jsonObject = new JSONObject(beaconMessage);
                lat = Double.parseDouble(jsonObject.getString("latitude"));
                lon = Double.parseDouble(jsonObject.getString("longitude"));
                addLocationMarker(lat, lon);
                Log.d("TAG", "Beacon message Found : " + lat + "=" + lon);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("TAG", "Beacon message Found : " + beaconMessage);
        }

        @Override
        public void onLost(Message message) {
            super.onLost(message);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.mapView.onCreate(savedInstanceState);
        if (mapValue == 0) {
            binding.rlButtons.setVisibility(View.GONE);
        }
        binding.mapView.onResume();
        binding.mapView.getMapAsync(this);

        mLocationService = new LocationService(getActivity());
        mPolylineOptions = new PolylineOptions();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Nearby.MESSAGES_API, new MessagesOptions.Builder().setPermissions(NearbyPermissions.BLE).build())
                .addConnectionCallbacks(this)
                .enableAutoManage((FragmentActivity) getActivity(), this)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        Log.d("TAG", "GoogleApiClient connection start");
    }

    public void onTakePictureClick() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 1);
    }

    public void onSkipClick() {
        ScoreCardDialog scoreCardDialog = new ScoreCardDialog(getActivity());
        scoreCardDialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void addLocationMarker(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mPolylineOptions.add(latLng);
        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.addPolyline(mPolylineOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 25));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        subscribe();
    }

    private void subscribe() {
        //Subscription for foreground messsage
        SubscribeOptions options = new SubscribeOptions.Builder().setStrategy(Strategy.BLE_ONLY).build();
        Nearby.Messages.subscribe(mGoogleApiClient, mMessageListenear, options).setResultCallback(new ResultCallback<Status>() {

            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    Log.d("TAG", "subscrib successfully");
                } else {
                    Log.d("TAG", "subscrib unSuccessfully");
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(getActivity(), REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("TAG", "GoogleAPiClient connection failed");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fileName = "Camera";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            if (requestCode == 1) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                FileOutputStream fo = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                fo.write(stream.toByteArray());
                fo.close();
                fragmentReplaceCommunicator.fragmentReplace(new CameraSnapFragment());

            }
        } catch (Exception exception) {
        }
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            if (requestCode == Activity.RESULT_OK) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient.isConnected()) {
            Log.d("TAG", "GoogleApiClient connection stop");
            unSubscrib();
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private void unSubscrib() {
        Nearby.Messages.unsubscribe(mGoogleApiClient, mMessageListenear);
        Log.d("TAG", "Beacon UnSubscrib");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mRecevier, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mRecevier);
    }

    private final BroadcastReceiver mRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        bluetoothAdapter.enable();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        break;
                    case BluetoothAdapter.STATE_ON:
                        break;
                }
            }
        }
    };
}
