package lk.dhanushkaTa.travelApp.controller;

//import lk.dhanushkaTa.travelApp.dto.TravelPackageDTO;
import lk.dhanushkaTa.travelApp.dto.TravelPackageDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.service.TravelPackageService;
import lk.dhanushkaTa.travelApp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/travelPackage")
@RequiredArgsConstructor
public class TravelPackageController {

    @Autowired
    private final TravelPackageService travelPackageService;

    @GetMapping
    public String ping(){
        return "TravelPackage Controller Ok..";
    }

    @GetMapping(path = "find/{packageId}")
    public ResponseUtil findPackageById(@PathVariable String packageId){
        return new ResponseUtil("200","Package found",travelPackageService.findPackageById(packageId));
    }

    @GetMapping(path = "find/all")
    public ResponseUtil getAllPackages(){
        return new ResponseUtil("200","Package found",travelPackageService.getAllPackages());
    }

    @PostMapping(path = "save")
    public ResponseUtil savePackage(@RequestBody TravelPackageDTO packageDTO) throws DuplicateException {
        travelPackageService.saveTravelPackage(packageDTO);
        return new ResponseUtil("200","Package saved",null);
    }

    @PutMapping(path = "update")
    public ResponseUtil updatePackage(@RequestBody TravelPackageDTO packageDTO) throws NotFoundException {
        travelPackageService.updateTravelPackage(packageDTO);
        return new ResponseUtil("200","Package updated",null);
    }

    @DeleteMapping(path = "delete/{packageId}")
    public ResponseUtil deletePackage(@PathVariable String packageId) throws NotFoundException {
        travelPackageService.deletePackage(packageId);
        return new ResponseUtil("200","Package Deleted",null);
    }
}
