package lk.dhanushkaTa.travelApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.dhanushkaTa.travelApp.dto.UserDTO;
import lk.dhanushkaTa.travelApp.entity.Image;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.repository.ImageRepo;
import lk.dhanushkaTa.travelApp.service.UserService;
import lk.dhanushkaTa.travelApp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public final UserService userService;

    @Autowired
    public final ObjectMapper objectMapper;

    @GetMapping()
    public String getTest(){
        return "user controller Ok...";
    }

    @GetMapping(path = "find/{userId}")
    public ResponseUtil findUserById(@PathVariable("userId") String userId) throws NotFoundException {
        UserDTO userById = userService.findUserById(userId);

//        HttpHeaders headers=new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(userById.getProfileImage(),headers, HttpStatus.OK);

        return new ResponseUtil("200","User Find",userById);
    }

    @GetMapping(path = "find/all")
    public ResponseUtil getAll(){

//        List<UserDTO> all = userService.findAll();
//        HttpHeaders headers=new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(all.get(1).getNicImage1(),headers, HttpStatus.OK);
        List<UserDTO> all = userService.findAll();
        for (UserDTO userDTO : all){
            System.out.println(userDTO.getUserId()+" : "+userDTO.getUserIdNum());
        }

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

    @PostMapping(path = "save",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil saveUser(@RequestParam String user,
                                 @RequestParam MultipartFile nic1,
                                 @RequestParam MultipartFile nic2,
                                 @RequestParam MultipartFile pic) throws DuplicateException, JsonProcessingException {
        System.out.println("userDto : "+user);

        UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);
        System.out.println(userDTO);
        userService.saveUser(userDTO,nic1,nic2,pic);
        return new ResponseUtil("201","User Saved",null);
    }

    @DeleteMapping(path = "delete/{userId}")
    public ResponseUtil deleteUser(@PathVariable("userId")String userId) throws NotFoundException {
        userService.deleteUserById(userId);
        return new ResponseUtil("200","User Deleted",null);
    }


    @PutMapping(path = "update",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil updateUser(@RequestParam String user,
                                   @RequestParam MultipartFile nic1,
                                   @RequestParam MultipartFile nic2,
                                   @RequestParam MultipartFile pic) throws NotFoundException, JsonProcessingException {
        System.out.println("userDto : "+user);

        UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);
        System.out.println(userDTO);
        userService.updateUser(userDTO,nic1,nic2,pic);
        return new ResponseUtil("200","User updated",null);
    }

    @Autowired
    private final ImageRepo imageRepo;

    @PostMapping(path = "file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE})//@RequestParam MultipartFile file
    public ResponseUtil saveFile
            (@RequestParam MultipartFile file,@RequestParam String id) throws IOException {
        String newId=id;
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadPathDer="E:\\IJSE\\AAD\\image\\"+newId;
        Path uploadPath = Paths.get(uploadPathDer);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }else {
            FileUtils.deleteDirectory(new File(uploadPathDer));
            Files.createDirectories(uploadPath);
        }
        System.out.println("*******");
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadPath +"\\"+ file.getOriginalFilename());
        System.out.println(path.toFile().getAbsolutePath());
        System.out.println(path.toFile().toURI());
        Files.write(path,bytes);

        imageRepo.save(new Image(newId,path.toString()));

//        URI uri = path.toFile().toURI();
//        String string = ServletUriComponentsBuilder.fromCurrentContextPath().path(uri.getPath()).path(file.getOriginalFilename()).toUriString();
//        System.out.println(string);

        return new ResponseUtil();
    }

    @GetMapping(path = "img/{imgId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imgId){
        String fileName = imageRepo.findById(imgId).get().getFileName();
        Path path = Paths.get(fileName);
        File file = path.toFile();
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(bytes,headers, HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
