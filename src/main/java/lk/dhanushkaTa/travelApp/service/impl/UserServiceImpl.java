package lk.dhanushkaTa.travelApp.service.impl;

import lk.dhanushkaTa.travelApp.dto.UserDTO;
import lk.dhanushkaTa.travelApp.entity.User;
import lk.dhanushkaTa.travelApp.exception.DuplicateException;
import lk.dhanushkaTa.travelApp.exception.NotFoundException;
import lk.dhanushkaTa.travelApp.repository.UserRepository;
import lk.dhanushkaTa.travelApp.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    public final UserRepository userRepository;

    @Autowired
    public final ModelMapper modelMapper;

    @Override
    public boolean saveUser(UserDTO userDTO) throws DuplicateException {
        if(userDTO==null){
            throw new RuntimeException("UserDto null From UserService");
        }

        if (userRepository.findById(userDTO.getUserId()).isPresent()) {//return Optional<User>
            throw new DuplicateException("Customer AllReady Exists From UserService");
        }
        userRepository.save(modelMapper.map(userDTO, User.class));
        return true;
    }

    @Override
    public UserDTO findUserById(String userId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new NotFoundException("Customer Couldn't find");
        }
        return modelMapper.map(user.get(), UserDTO.class);
    }

    @Override
    public UserDTO findUserByIdOrNic(String detail) {
        User user= userRepository.findUserByUserIdOrUserNic(detail, detail);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findUserByIdOrNicLike(String detail) {
        return modelMapper.map(userRepository.findUserByUserIdLikeOrUserNicLike(detail,detail),
                new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public void updateUser(UserDTO userDTO) throws NotFoundException {
        if (!userRepository.existsById(userDTO.getUserId())){
            throw new NotFoundException("User couldn't found");
        }
        userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public List<UserDTO> findAll() {
        return modelMapper.map(userRepository.findAll(),new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public void deleteUserById(String userId) throws NotFoundException {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }else {
            throw new NotFoundException("User not found");
        }
    }
}
