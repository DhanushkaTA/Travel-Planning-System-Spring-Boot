package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    VehicleDTO findVehicleById(String vehicleId);

    List<VehicleDTO> getAllVehicle();

    List<VehicleDTO> getAllVehicleBySorting(String direction, String properties,String type);

    void saveVehicle(VehicleDTO vehicleDTO);

    void updateVehicle(VehicleDTO vehicleDTO);

    void deleteVehicle(String vehicleId);

    void updateVehicleStatus(String vehicleId);
}
