package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    void saveHotel(HotelDTO hotelDTO);

    HotelDTO findHotelById(String hotelId);

    List<HotelDTO> getHotelList();
}
