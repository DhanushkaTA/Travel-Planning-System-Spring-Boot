package lk.dhanushkaTa.travelApp.controller;

import lk.dhanushkaTa.travelApp.dto.HotelDTO;
import lk.dhanushkaTa.travelApp.service.HotelService;
import lk.dhanushkaTa.travelApp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "find/{name}")
    public ResponseUtil findHotelByName(@PathVariable String name){
        return new ResponseUtil("200","Hotel found",hotelService.findHotelByName(name));
    }

    @GetMapping(path = "find/like/{name}")
    public ResponseUtil findHotelByNameLike(@PathVariable String name){
        return new ResponseUtil("200","Hotel found",hotelService.findHotelByNameLike(name));
    }

    @PostMapping(path = "save")
    public ResponseUtil saveHotel(@RequestBody HotelDTO hotelDTO){
        hotelService.saveHotel(hotelDTO);
        return new ResponseUtil("200","Hotel Saved",null);
    }

    @PutMapping(path = "update")
    public ResponseUtil updateHotel(@RequestBody HotelDTO hotelDTO){
        hotelService.updateHotelDetails(hotelDTO);
        return new ResponseUtil("200","Hotel updated",null);
    }

    @DeleteMapping(path = "delete/{hotelId}")
    public ResponseUtil deleteHotel(@PathVariable String hotelId){
        hotelService.deleteHotel(hotelId);
        return new ResponseUtil("200","Hotel Deleted",null);
    }

}
