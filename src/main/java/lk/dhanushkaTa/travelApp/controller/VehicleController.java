package lk.dhanushkaTa.travelApp.controller;

import lk.dhanushkaTa.travelApp.dto.VehicleDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.service.VehicleService;
import lk.dhanushkaTa.travelApp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    @GetMapping
    public String ping(){
        return "Vehicle Controller ok...";
    }

    @GetMapping(path = "find/{vehicleId}")
    public ResponseUtil findVehicleById(@PathVariable String vehicleId){
        return new ResponseUtil("200","Vehicle Found",vehicleService.findVehicleById(vehicleId));
    }

    @GetMapping(path = "find/all")
    public ResponseUtil getAllVehicles(){
        return new ResponseUtil("200","Vehicle list",vehicleService.getAllVehicle());
    }

    //  find/filter?direction=asc&properties=seat&type=all
    @GetMapping(path = "find/filter",params = {"direction","properties","type"})
    public ResponseUtil getVehicleListByOrder(String direction,String properties,String type){
        return new ResponseUtil(
                "200",
                "Vehicle list",
                vehicleService
                        .getAllVehicleBySorting(direction,properties,type));
    }

    @PostMapping(path = "save")
    public ResponseUtil saveVehicle(@RequestBody VehicleDTO vehicleDTO) throws DuplicateException {
        vehicleService.saveVehicle(vehicleDTO);
        return new ResponseUtil("200","Vehicle saved",null);
    }

    @PutMapping(path = "update")
    public ResponseUtil updateVehicle(@RequestBody VehicleDTO vehicleDTO) throws NotFoundException {
        vehicleService.updateVehicle(vehicleDTO);
        return new ResponseUtil("200","Vehicle updated",null);
    }

    @PutMapping(path = "update/status/{vehicleStatus}")
    public ResponseUtil updateVehicleStatus(@PathVariable String vehicleStatus) throws NotFoundException {
        vehicleService.updateVehicleStatus(vehicleStatus);
        return new ResponseUtil("200","Vehicle status updated",null);
    }

    @DeleteMapping("delete/{vehicleId}")
    public ResponseUtil deleteVehicle(@PathVariable String vehicleId) throws NotFoundException {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseUtil("200","Vehicle deleted",null);
    }
}
