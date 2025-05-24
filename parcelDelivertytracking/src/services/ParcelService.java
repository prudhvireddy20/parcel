package services;

//package parcel_tracking.services;

import exceptions.ParcelNotFoundException;
import models.*;

import java.util.HashMap;
import java.util.Map;

public class ParcelService {
    private Map<String, Parcel> parcels = new HashMap<>();

    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelId(), parcel);
    }

    public Parcel getParcel(String parcelId) throws ParcelNotFoundException {
        if (!parcels.containsKey(parcelId)) {
            throw new ParcelNotFoundException("Parcel not found.");
        }
        return parcels.get(parcelId);
    }
}
