package lk.dhanushkaTa.travelApp.service.impl;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;
import lk.dhanushkaTa.travelApp.entity.Hotel;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.repository.HotelRepository;
import lk.dhanushkaTa.travelApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public void saveHotel(HotelDTO hotelDTO, MultipartFile pic) throws DuplicateException {
        if (hotelRepository.existsById(hotelDTO.getHotelId())){
            throw new DuplicateException("Hotel Id already exits!");
        }
        Hotel hotel = this.handleFile(pic, modelMapper.map(hotelDTO, Hotel.class));
//        hotelRepository.save(modelMapper.map(hotelDTO, Hotel.class));
        hotelRepository.save(hotel);
    }

    @Override
    public HotelDTO findHotelById(String hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        return hotel.map(this::convertPathToByte).orElse(null);
//        return hotel.map(value -> modelMapper.map(value, HotelDTO.class)).orElse(null);
    }

    private HotelDTO convertPathToByte(Hotel hotel) {

        HotelDTO hotelDTO = modelMapper.map(hotel, HotelDTO.class);
        try {
            hotelDTO.setHotelImage(Files.readAllBytes(Paths.get(hotel.getHotelImage()).toFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hotelDTO;
    }

    @Override
    public List<HotelDTO> getHotelList() {

//        List<HotelDTO> collect = hotelRepository.findAll().stream().
//                map(this::convertPathToByte).collect(Collectors.toList());
        return hotelRepository.findAll().stream().
                map(this::convertPathToByte).collect(Collectors.toList());
//        return modelMapper.map(hotelRepository.findAll(),new TypeToken<List<HotelDTO>>(){}.getType());
    }

    @Override
    public List<HotelDTO> findHotelByNameLike(String hotelName) {
        List<Hotel> hotelList = hotelRepository.findByHotelNameIsLikeIgnoreCaseOrderByHotelCategoryAsc(hotelName);
        if (hotelList.isEmpty()){
            return null;
        }else {
            return hotelList.stream().map(this::convertPathToByte).collect(Collectors.toList());//return hotelDto list
//            return modelMapper.map(hotelList, new TypeToken<List<HotelDTO>>(){}.getType());
        }
    }

    public HotelDTO findHotelByName(String hotelName){
//        List<Hotel> hotel = hotelRepository.findByHotelNameIsLikeIgnoreCase(hotelName);
//        Hotel hotel = hotelRepository.findByHotelName(hotelName);
        Hotel hotel = hotelRepository.findByHotelNameIgnoreCase(hotelName);

        return hotel!=null ? this.convertPathToByte(hotel) : null;
//        return hotel!=null ? modelMapper.map(hotel,HotelDTO.class) : null;
//        return hotel.map(value -> modelMapper.map(value,HotelDTO.class)).orElse(null);
    }

    @Override
    public void updateHotelDetails(HotelDTO hotelDTO,MultipartFile pic) throws NotFoundException {
        if (!hotelRepository.existsById(hotelDTO.getHotelId())){
            throw new NotFoundException("Hotel couldn't find");
        }
        Hotel hotel = this.handleFile(pic, modelMapper.map(hotelDTO, Hotel.class));
//        hotelRepository.save(modelMapper.map(hotelDTO, Hotel.class));
        hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(String hotelId) throws NotFoundException {
        if (!hotelRepository.existsById(hotelId)){
            throw new NotFoundException("Hotel couldn't find");
        }
        hotelRepository.deleteById(hotelId);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Hotel handleFile(MultipartFile pic,Hotel hotel){

        try {
            String uploadPathDer="E:\\IJSE\\AAD\\image\\hotel\\"+hotel.getHotelId();
            Path uploadPath = Paths.get(uploadPathDer);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }else {
                FileUtils.deleteDirectory(new File(uploadPathDer));
                Files.createDirectories(uploadPath);
            }

            for (int i=0;i<3;i++){
                byte[] bytes = pic.getBytes();
                Path path = Paths.get(uploadPath +"\\"+ pic.getOriginalFilename());
                Files.write(path,bytes);
                hotel.setHotelImage(path.toString());
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }



}
