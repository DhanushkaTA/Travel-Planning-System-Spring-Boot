package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;

import java.util.List;

public interface HotelService {
    void saveHotel(HotelDTO hotelDTO) throws DuplicateException;

    HotelDTO findHotelById(String hotelId);

    List<HotelDTO> getHotelList();

    List<HotelDTO> findHotelByNameLike(String hotelName);

   HotelDTO findHotelByName(String hotelName);

   void updateHotelDetails(HotelDTO hotelDTO) throws NotFoundException;

   void deleteHotel(String hotelId) throws NotFoundException;
}
