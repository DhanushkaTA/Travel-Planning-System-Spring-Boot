package lk.dhanushkaTa.travelApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private String vehicleId;

    @Column(nullable = false)
    private String vehicleBrand;

    @Column(nullable = false)
    private String vehicleCategory;

    @Column(nullable = false)
    private String vehicleHybridOrNot;

    @Column(nullable = false)
    private String vehicleFuelType;

    @Column(nullable = false)
    private int    vehicleFuelUsage;

    @Column(nullable = false)
    private int    vehicleSeatCapacity;

    @Column(nullable = false)
    private String vehicleTransmissionType;

    @Column(nullable = false)
    private double vehicleFee_for_1km;

    @Column(nullable = false)
    private double vehicleFee_for_Day;

    @Column(nullable = false)
    private String vehicleStatus;

    @Column(nullable = false)
    private String vehicleDriverName;

    @Column(nullable = false)
    private String vehicleDriverContact;
//    private String vehicleDriverLicense;
//    private String vehicleImage;
}
