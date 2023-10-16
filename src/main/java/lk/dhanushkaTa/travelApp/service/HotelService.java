package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    void saveHotel(HotelDTO hotelDTO);

    HotelDTO findHotelById(String hotelId);

    List<HotelDTO> getHotelList();

    List<HotelDTO> findHotelByNameLike(String hotelName);

   HotelDTO findHotelByName(String hotelName);

   void updateHotelDetails(HotelDTO hotelDTO);

   void deleteHotel(String hotelId);
}
