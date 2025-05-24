package services;

import exceptions.InvalidParcelStatusException;
import models.Parcel;

import java.util.List;

public class TrackingService {
    private static final List<String> VALID_STATUSES = List.of("Registered", "In Transit", "Delivered", "Cancelled");

    public void updateParcelStatus(Parcel parcel, String newStatus) throws InvalidParcelStatusException {
        if (!VALID_STATUSES.contains(newStatus)) {
            throw new InvalidParcelStatusException("Invalid status update.");
        }

        parcel.updateStatus(newStatus);
    }
}
