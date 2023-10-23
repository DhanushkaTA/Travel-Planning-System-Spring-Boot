package lk.dhanushkaTa.travelApp.repository;

import lk.dhanushkaTa.travelApp.entity.Vehicle;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,String> {

//    List<Vehicle> findAllByVehicleTransmissionTypeOrderByVehicleTransmissionTypeAsc(String type);

    List<Vehicle> findByOrderByVehicleSeatCapacityDesc();

    List<Vehicle> findByVehicleTransmissionTypeOrderByVehicleSeatCapacityDesc(String transmissionType);

    List<Vehicle> findByVehicleTransmissionTypeOrderByVehicleIdAsc(String transmissionType);

    List<Vehicle> findByVehicleFuelTypeIsLike(String fuelType);

    List<Vehicle> findByVehicleFuelTypeAndVehicleTransmissionTypeOrderByVehicleBrand(
            String fuelType,String transmissionType);
}
