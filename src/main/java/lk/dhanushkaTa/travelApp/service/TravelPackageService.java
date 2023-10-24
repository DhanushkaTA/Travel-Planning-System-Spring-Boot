package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.TravelPackageDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;

import java.util.List;

public interface TravelPackageService {

    void saveTravelPackage(TravelPackageDTO travelPackageDTO) throws DuplicateException;

    void updateTravelPackage(TravelPackageDTO travelPackageDTO) throws NotFoundException;

    TravelPackageDTO findPackageById(String packageId);

    List<TravelPackageDTO> getAllPackages();

    void deletePackage(String packageId) throws NotFoundException;

    String getNextId();

}
