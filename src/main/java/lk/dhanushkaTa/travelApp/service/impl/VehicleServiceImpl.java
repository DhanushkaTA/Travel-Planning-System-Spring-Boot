package lk.dhanushkaTa.travelApp.service.impl;

import lk.dhanushkaTa.travelApp.dto.VehicleDTO;
import lk.dhanushkaTa.travelApp.entity.Vehicle;
import lk.dhanushkaTa.travelApp.repository.VehicleRepository;
import lk.dhanushkaTa.travelApp.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private final VehicleRepository vehicleRepository;

    @Autowired
    private final ModelMapper modelMapper;

    private  Sort.Direction direction=Sort.Direction.ASC;

    private String properties="id";

    private String transmissionType="auto";


    @Override
    public VehicleDTO findVehicleById(String vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        return vehicle.map(value -> modelMapper.map(value, VehicleDTO.class)).orElse(null);
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return modelMapper.map(vehicleRepository.findAll(),new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    @Override
    public List<VehicleDTO> getAllVehicleBySorting(String direction, String properties,String type) {
        this.properties = properties.equalsIgnoreCase("seat") ? "vehicleSeatCapacity" :
                properties.equalsIgnoreCase("fuelType") ? "vehicleFuelType" :
                        properties.equalsIgnoreCase("transmissionType") ?
                                "vehicleTransmissionType" : "id";
        this.direction = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        System.out.println(" \n"+this.properties+" \n");
        System.out.println(" \n"+this.direction+" \n");
        System.out.println(" \n"+this.transmissionType+" \n");

        if (properties.equalsIgnoreCase("transmissionType")){
            this.transmissionType=type;
            return modelMapper.map(vehicleRepository
                    .findByVehicleTransmissionTypeOrderByVehicleIdAsc(this.transmissionType),
                    new TypeToken<List<VehicleDTO>>(){}.getType());
        }

        return modelMapper.map(vehicleRepository
                .findAll(Sort.by(this.direction,this.properties)),new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepository.existsById(vehicleDTO.getVehicleId())){
            throw new RuntimeException("Vehicle number already exits");
        }
        vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleRepository.existsById(vehicleDTO.getVehicleId())){
            throw new RuntimeException("Vehicle number not found");
        }
        vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class));
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)){
            throw new RuntimeException("Vehicle number not found");
        }
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public void updateVehicleStatus(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)){
            throw new RuntimeException("Vehicle number not found");
        }
        Optional<Vehicle> vehicleById = vehicleRepository.findById(vehicleId);
        if (vehicleById.isPresent()){
            Vehicle vehicle = vehicleById.get();
            if (vehicle.getVehicleStatus().equalsIgnoreCase("eligible")){
                vehicle.setVehicleStatus("NotEligible");
            }else {
                vehicle.setVehicleStatus("Eligible");
            }
            vehicleRepository.save(vehicle);
        }
    }
}
