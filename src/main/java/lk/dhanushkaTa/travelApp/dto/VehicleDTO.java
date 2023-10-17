package lk.dhanushkaTa.travelApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VehicleDTO {

    private String vehicleId;
    private String vehicleBrand;
    private String vehicleCategory;
    private String vehicleHybridOrNot;
    private String vehicleFuelType;
    private int    vehicleFuelUsage;
    private int    vehicleSeatCapacity;
    private double vehicleFee_for_1km;
    private double vehicleFee_for_Day;
    private String vehicleStatus;
    private String vehicleTransmissionType;
    private String vehicleDriverName;
    private String vehicleDriverContact;
//    private String vehicleDriverLicense;
//    private String vehicleImage;
}
