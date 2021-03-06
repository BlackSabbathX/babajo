package com.plasmadev.captiondad.carddashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class Lugares extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLngBounds lim = new LatLngBounds(
                new LatLng(10.9856283, -74.7946667),
                new LatLng(10.9958233, -74.7768137)
        );
        mMap.setMinZoomPreference(14.5f);
        mMap.setLatLngBoundsForCameraTarget(lim);

        for (DocumentSnapshot d : FirebaseHolder.places) {
            mMap.addMarker(
                    new MarkerOptions().position(
                            new LatLng(
                                    Double.parseDouble(d.get("lat").toString()),
                                    Double.parseDouble(d.get("lng").toString())
                            )).title(d.getString("nombre")));
        }

//        LatLng sydney = new LatLng(10.991047, -74.783465);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Parque "));
//
//        LatLng carnaval = new LatLng(10.988378, -74.778882);
//        mMap.addMarker(new MarkerOptions().position(carnaval).title("Aduana"));
//
//        LatLng tienda = new LatLng(10.991901, -74.786241);
//        mMap.addMarker(new MarkerOptions().position(tienda).title("Billares el callejon"));
//
//        LatLng tienda2 = new LatLng(10.992447, -74.784913);
//        mMap.addMarker(new MarkerOptions().position(tienda2).title("Fritos Barrio Abajo"));
//
//        LatLng tienda3 = new LatLng(10.993283, -74.786811);
//        mMap.addMarker(new MarkerOptions().position(tienda3).title("Donde Eddier`Sabor "));
    }
}
