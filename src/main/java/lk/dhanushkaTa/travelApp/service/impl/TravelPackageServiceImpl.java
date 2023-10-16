package lk.dhanushkaTa.travelApp.service.impl;

import lk.dhanushkaTa.travelApp.dto.TravelPackageDTO;
import lk.dhanushkaTa.travelApp.entity.TravelPackage;
import lk.dhanushkaTa.travelApp.repository.TravelPackageRepository;
import lk.dhanushkaTa.travelApp.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired
    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public void saveTravelPackage(TravelPackageDTO travelPackageDTO) {
        if (travelPackageRepository.existsById(travelPackageDTO.getTravelPackage_Id())){
            throw new RuntimeException("Travel Package Id Already Exits");
        }
        travelPackageRepository.save(modelMapper.map(travelPackageDTO, TravelPackage.class));
    }

    @Override
    public void updateTravelPackage(TravelPackageDTO travelPackageDTO) {
        if (!travelPackageRepository.existsById(travelPackageDTO.getTravelPackage_Id())){
            throw new RuntimeException("Travel Package couldn't found");
        }
        travelPackageRepository.save(modelMapper.map(travelPackageDTO, TravelPackage.class));
    }

    @Override
    public TravelPackageDTO findPackageById(String packageId) {
        Optional<TravelPackage> aPackage = travelPackageRepository.findById(packageId);
        return aPackage.map(value -> modelMapper.map(value, TravelPackageDTO.class)).orElse(null);
    }

    @Override
    public List<TravelPackageDTO> getAllPackages() {
        return modelMapper.map(travelPackageRepository.findAll(),new TypeToken<List<TravelPackageDTO>>(){}.getType());
    }

    @Override
    public void deletePackage(String packageId) {
        if (!travelPackageRepository.existsById(packageId)){
            throw new RuntimeException("Travel Package couldn't found");
        }
        travelPackageRepository.deleteById(packageId);
    }
}
