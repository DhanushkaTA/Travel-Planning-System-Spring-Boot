package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.GuideDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;

import java.util.List;

public interface GuideService {

    GuideDTO findGuideById(String guideId);

    List<GuideDTO> getAllGuides();

    void saveGuide(GuideDTO guideDTO) throws DuplicateException;

    void updateGuide(GuideDTO guideDTO) throws NotFoundException;

    void deleteGuide(String guideId) throws NotFoundException;

    void updateGuideStatus(String guideId) throws NotFoundException;
}
