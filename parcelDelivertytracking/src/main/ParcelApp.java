package main;

import models.*;
import services.*;
import exceptions.*;

import java.util.Scanner;
import java.time.LocalDate;

public class ParcelApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ParcelService parcelService = new ParcelService();
    private static final TrackingService trackingService = new TrackingService();
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static void main(String[] args) {
        while (true) {
            System.out.println("--- Parcel Delivery Tracker ---");
            System.out.println(ANSI_YELLOW+"1. Add Parcel");
            System.out.println(ANSI_YELLOW+"2. Update Parcel Status");
            System.out.println(ANSI_YELLOW+"3. View Parcel Details");
            System.out.println(ANSI_YELLOW+"4. Exit");
            System.out.print(ANSI_YELLOW+"Choose option: ");

            switch (scanner.nextInt()) {
                case 1 -> addParcel();
                case 2 -> updateStatus();
                case 3 -> viewParcel();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addParcel() {
        System.out.print("Enter Parcel ID: ");
        String id = scanner.next();

        System.out.print("Sender Name: ");
        String sName = scanner.next();
        System.out.print("Sender Contact: ");
        String sContact = scanner.next();
        System.out.print("Sender Address: ");
        scanner.nextLine();  
        String sAddress = scanner.nextLine();

        System.out.print("Receiver Name: ");
        String rName = scanner.next();
        System.out.print("Receiver Contact: ");
        String rContact = scanner.next();
        System.out.print("Receiver Address: ");
        scanner.nextLine();
        String rAddress = scanner.nextLine();

        System.out.print("Weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Initial Status: ");
        String status = scanner.next();

        System.out.print("Expected Delivery Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());

        Customer sender = new Customer(sName, sContact, sAddress);
        Customer receiver = new Customer(rName, rContact, rAddress);
        Parcel parcel = new Parcel(id, sender, receiver, weight, status, date);
        parcelService.addParcel(parcel);

        System.out.println("Parcel added.");
    }

    private static void updateStatus() {
        try {
            System.out.print("Enter Parcel ID: ");
            String id = scanner.next();

            Parcel parcel = parcelService.getParcel(id);

            System.out.print("New Status (In Transit / Delivered / Cancelled): ");
            String newStatus = scanner.next();

            trackingService.updateParcelStatus(parcel, newStatus);
            System.out.println("Status updated successfully.");
        } catch (ParcelNotFoundException | InvalidParcelStatusException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewParcel() {
        try {
            System.out.print("Enter Parcel ID: ");
            String id = scanner.next();

            Parcel parcel = parcelService.getParcel(id);
            System.out.printf("ID: %s%nSender: %s → Receiver: %s%nWeight: %.2fkg%nStatus: %s%nDelivery Date: %s%n",
                    parcel.getParcelId(),
                   // parcel.getSender().getName(),
                    //parcel.getReceiver().getName(),
                    parcel.getSender(),
                    parcel.getReceiver(),
                    parcel.getWeight(),
                    parcel.getStatus(),
                    parcel.getDeliveryDate());

            System.out.println("Status History: " + parcel.getStatusLog());
        } catch (ParcelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
