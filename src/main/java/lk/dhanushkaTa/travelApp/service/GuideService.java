package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.GuideDTO;

import java.util.List;

public interface GuideService {

    GuideDTO findGuideById(String guideId);

    List<GuideDTO> getAllGuides();

    void saveGuide(GuideDTO guideDTO);

    void updateGuide(GuideDTO guideDTO);

    void deleteGuide(String guideId);

    void updateGuideStatus(String guideId);
}
