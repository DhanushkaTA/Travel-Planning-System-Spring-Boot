package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.TravelPackageDTO;

import java.util.List;

public interface TravelPackageService {

    void saveTravelPackage(TravelPackageDTO travelPackageDTO);

    void updateTravelPackage(TravelPackageDTO travelPackageDTO);

    TravelPackageDTO findPackageById(String packageId);

    List<TravelPackageDTO> getAllPackages();

    void deletePackage(String packageId);

}
