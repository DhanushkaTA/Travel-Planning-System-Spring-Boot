package lk.dhanushkaTa.travelApp.service;

import lk.dhanushkaTa.travelApp.dto.UserDTO;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;

import java.util.List;

public interface UserService {
    boolean saveUser(UserDTO userDTO) throws DuplicateException;

    UserDTO findUserById(String userId) throws NotFoundException;

    List<UserDTO> findAll();

    void deleteUserById(String userId) throws NotFoundException;

    UserDTO findUserByIdOrNic(String detail);

    List<UserDTO> findUserByIdOrNicLike(String detail);

    void updateUser(UserDTO userDTO) throws NotFoundException;
}
