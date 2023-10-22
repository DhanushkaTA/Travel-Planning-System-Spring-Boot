package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HotelService {
    void saveHotel(HotelDTO hotelDTO, MultipartFile pic) throws DuplicateException;

    HotelDTO findHotelById(String hotelId);

    List<HotelDTO> getHotelList();

    List<HotelDTO> findHotelByNameLike(String hotelName);

   HotelDTO findHotelByName(String hotelName);

   void updateHotelDetails(HotelDTO hotelDTO,MultipartFile pic) throws NotFoundException;

   void deleteHotel(String hotelId) throws NotFoundException;
}
