package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.UserDTO;

import java.util.List;

public interface UserService {
    boolean saveUser(UserDTO userDTO);

    UserDTO findUserById(String userId);

    List<UserDTO> findAll();

    void deleteUserById(String userId);

    UserDTO findUserByIdOrNic(String detail);

    List<UserDTO> findUserByIdOrNicLike(String detail);

    void updateUser(UserDTO userDTO);
}
