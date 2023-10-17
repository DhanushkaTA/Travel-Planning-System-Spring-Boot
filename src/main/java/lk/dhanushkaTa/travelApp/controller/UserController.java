package lk.dhanushkaTa.travelApp.controller;

import lk.dhanushkaTa.travelApp.dto.UserDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.service.UserService;
import lk.dhanushkaTa.travelApp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public final UserService userService;

    @GetMapping()
    public String getTest(){
        return "user controller Ok...";
    }

    @GetMapping(path = "find/{userId}")
    public ResponseUtil findUserById(@PathVariable("userId") String userId) throws NotFoundException {
        UserDTO userById = userService.findUserById(userId);
        return new ResponseUtil("200","User Find",userById);
    }

    @GetMapping(path = "find/all")
    public ResponseUtil getAll(){
        return new ResponseUtil("200","User List",userService.findAll());
    }

    @GetMapping(params = {"detail"})//api/v1/user?detail=xxxxx
    private ResponseUtil findUserByIdOrNic(@RequestParam("detail") String detail){
        return new ResponseUtil("200","User found",userService.findUserByIdOrNic(detail));
    }

//    @GetMapping(params = {"detail"})//api/v1/user?detail=xxxxx
//    private ResponseUtil findUserByIdOrNic(@RequestParam("detail") String detail){
//        return new ResponseUtil("200","User found",userService.findUserByIdOrNicLike(detail));
//    }

    @PostMapping(path = "save",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveUser(@RequestBody UserDTO userDto) throws DuplicateException {
        System.out.println("userDto : "+userDto);
        userService.saveUser(userDto);
        return new ResponseUtil("201","User Saved",null);
    }

    @DeleteMapping(path = "delete/{userId}")
    public ResponseUtil deleteUser(@PathVariable("userId")String userId) throws NotFoundException {
        userService.deleteUserById(userId);
        return new ResponseUtil("200","User Deleted",null);
    }

    @PutMapping
    public ResponseUtil updateUser(@RequestBody UserDTO userDTO) throws NotFoundException {
        userService.updateUser(userDTO);
        return new ResponseUtil("200","User updated",null);
    }
}
