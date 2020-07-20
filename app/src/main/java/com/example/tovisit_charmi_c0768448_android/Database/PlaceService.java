package com.example.tovisit_charmi_c0768448_android.Database;

import com.example.tovisit_charmi_c0768448_android.Model.Place;

import java.util.List;

public interface PlaceService {
    List<Place> getAll();

    void insertAll(Place... places);

    void delete(Place place);

    void update(Place place);
}
