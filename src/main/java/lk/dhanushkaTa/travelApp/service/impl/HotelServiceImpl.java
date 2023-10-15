package lk.dhanushkaTa.travelApp.service.impl;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;
import lk.dhanushkaTa.travelApp.entity.Hotel;
import lk.dhanushkaTa.travelApp.repository.HotelRepository;
import lk.dhanushkaTa.travelApp.service.HotelService;
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
public class HotelServiceImpl implements HotelService {

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public void saveHotel(HotelDTO hotelDTO) {
        if (hotelRepository.existsById(hotelDTO.getHotelId())){
            throw new RuntimeException("Hotel Id already exits!");
        }
        hotelRepository.save(modelMapper.map(hotelDTO, Hotel.class));
    }

    @Override
    public HotelDTO findHotelById(String hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        return hotel.map(value -> modelMapper.map(value, HotelDTO.class)).orElse(null);
    }

    @Override
    public List<HotelDTO> getHotelList() {
        return modelMapper.map(hotelRepository.findAll(),new TypeToken<List<HotelDTO>>(){}.getType());
    }
}