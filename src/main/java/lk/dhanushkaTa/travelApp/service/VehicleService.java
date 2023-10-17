package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.VehicleDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;

import java.util.List;

public interface VehicleService {

    VehicleDTO findVehicleById(String vehicleId);

    List<VehicleDTO> getAllVehicle();

    List<VehicleDTO> getAllVehicleBySorting(String direction, String properties,String type);

    void saveVehicle(VehicleDTO vehicleDTO) throws DuplicateException;

    void updateVehicle(VehicleDTO vehicleDTO) throws NotFoundException;

    void deleteVehicle(String vehicleId) throws NotFoundException;

    void updateVehicleStatus(String vehicleId) throws NotFoundException;
}
