package com.example.tovisit_charmi_c0768448_android.Utility;

import android.os.AsyncTask;

import com.example.tovisit_charmi_c0768448_android.Model.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.IOException;

import static com.example.tovisit_charmi_c0768448_android.Utility.GoogleAPIStore.*;

public class GetLocation  extends AsyncTask<Object, Place, Place> {
    GoogleMap mMap;

    @Override
    protected Place doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        String searchText = (String) objects[1];
        Place place = null;
        try {
            place = FetchLocation(searchText);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }

    @Override
    protected void onPostExecute(Place place) {
        super.onPostExecute(place);
        if (place == null) { return; }
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions()
                .position(place.getLocation())
                .title(place.getName() + " : " + place.getVicinity())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mMap.addMarker(markerOptions);
        // Move camera
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(place.getLocation())
                .zoom(15)
                .bearing(0)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
