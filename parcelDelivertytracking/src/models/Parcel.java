package models;

//package parcel_tracking.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parcel {
    private String parcelId;
    private Customer sender;
    private Customer receiver;
    private double weight;
    private String status;
    private LocalDate deliveryDate;
    private List<String> statusLog = new ArrayList<>();

    public Parcel(String parcelId, Customer sender, Customer receiver, double weight, String status, LocalDate deliveryDate) {
        this.parcelId = parcelId;
        this.sender = sender;
        this.receiver = receiver;
        this.weight = weight;
        this.status = status;
        this.deliveryDate = deliveryDate;
        statusLog.add(status);
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        statusLog.add(newStatus);
    }
	public String getParcelId() {
		return parcelId;
	}
	public Object getName()
	{
		return sender;
		
	}
	public Object getReceiver() {
		return receiver;
	}

	public Object getWeight() {
		return weight;
	}

	public Object getStatus() {
		return status;
	}

	public Object getDeliveryDate() {
		return deliveryDate;
	}
	public Object getStatusLog()
	{
		return status;
	}

	public Object getSender() {
		return sender;
	}
    
}
