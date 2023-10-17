package lk.dhanushkaTa.travelApp.service.impl;

import lk.dhanushkaTa.travelApp.dto.GuideDTO;
import lk.dhanushkaTa.travelApp.entity.Guide;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.repository.GuideRepository;
import lk.dhanushkaTa.travelApp.service.GuideService;
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
public class GuideServiceImpl implements GuideService {

    @Autowired
    private final GuideRepository guideRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public GuideDTO findGuideById(String guideId) {
        Optional<Guide> guide = guideRepository.findById(guideId);
        return guide.map(value -> modelMapper.map(value, GuideDTO.class)).orElse(null);
    }

    @Override
    public List<GuideDTO> getAllGuides() {
        return modelMapper.map(guideRepository.findAll(),new TypeToken<List<GuideDTO>>(){}.getType());
    }

    @Override
    public void saveGuide(GuideDTO guideDTO) throws DuplicateException {
        if (guideRepository.existsById(guideDTO.getGuideId())){
            throw new DuplicateException("Customer Already exits");
        }
        guideRepository.save(modelMapper.map(guideDTO, Guide.class));
    }

    @Override
    public void updateGuide(GuideDTO guideDTO) throws NotFoundException {
        if (!guideRepository.existsById(guideDTO.getGuideId())){
            throw new NotFoundException("Customer couldn't found");
        }
        guideRepository.save(modelMapper.map(guideDTO, Guide.class));
    }

    @Override
    public void deleteGuide(String guideId) throws NotFoundException {
        if (!guideRepository.existsById(guideId)){
            throw new NotFoundException("Customer couldn't found");
        }
        guideRepository.deleteById(guideId);
    }

    @Override
    public void updateGuideStatus(String guideId) throws NotFoundException {
        if (!guideRepository.existsById(guideId)){
            throw new NotFoundException("Customer couldn't found");
        }
        Optional<Guide> guideById = guideRepository.findById(guideId);
        Guide guide = guideById.get();
        if (guide.getGuideStatus().equalsIgnoreCase("eligible")){
            guide.setGuideStatus("NotEligible");
        }else {
            guide.setGuideStatus("Eligible");
        }
        guideRepository.save(guide);
    }
}
