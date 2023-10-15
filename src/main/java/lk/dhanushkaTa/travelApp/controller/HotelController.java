package lk.dhanushkaTa.travelApp.controller;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;
import lk.dhanushkaTa.travelApp.service.HotelService;
import lk.dhanushkaTa.travelApp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotel")
@RequiredArgsConstructor
@CrossOrigin
public class HotelController {

    @Autowired
    private final HotelService hotelService;

    @GetMapping
    public String ping(){
        return "Hotel Controller Ok";
    }

    @GetMapping(path = "all")
    public ResponseUtil getAllHotels(){
        return new ResponseUtil("200","Hotel List",hotelService.getHotelList());
    }

    @GetMapping(path = "{hotelId}")
    public ResponseUtil findHotelById(@PathVariable String hotelId){
        return new ResponseUtil("200","Hotel Found",hotelService.findHotelById(hotelId));
    }

    @PostMapping(path = "save")
    public ResponseUtil saveHotel(@RequestBody HotelDTO hotelDTO){
        hotelService.saveHotel(hotelDTO);
        return new ResponseUtil("200","Hotel Saved",null);
    }


}
