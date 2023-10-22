package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.GuideDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GuideService {

    GuideDTO findGuideById(String guideId);

    List<GuideDTO> getAllGuides();

    void saveGuide(GuideDTO guideDTO, MultipartFile nic,
                   MultipartFile guideId,MultipartFile pic) throws DuplicateException;
//    void saveGuide(GuideDTO guideDTO) throws DuplicateException;

    void updateGuide(GuideDTO guideDTO, MultipartFile nic,
                     MultipartFile guideId,MultipartFile pic) throws NotFoundException;

    void deleteGuide(String guideId) throws NotFoundException;

    void updateGuideStatus(String guideId) throws NotFoundException;
}
