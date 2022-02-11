package com.example.spring.ServiceImpl;

import com.example.spring.Service.UserService;
import com.example.spring.dao.Entity.User;
import com.example.spring.dao.Repository.LocationRepository;
import com.example.spring.dao.Repository.UserRepository;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.dto.GetUserDto;
import com.example.spring.dto.LocationInfoDto;
import com.example.spring.exception.LocationDoesNotExistException;
import com.example.spring.exception.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Override
    public void addUser(CreateUserDto dto) {
        User userForAdding = userConverter(dto);
        userRepository.save(userForAdding);
    }

    private User userConverter(CreateUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setLocation(locationRepository.findById(dto.getLocationId()).orElseThrow(() -> new LocationDoesNotExistException(
                "Location with id: " + dto.getLocationId() + " does not exist")));
        return user;
    }

    @Override
    public List<GetUserDto> getUser() {
        return userRepository.findAll().stream()
                .map(user -> GetUserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .locationInfoDto(LocationInfoDto.builder()
                                .id(user.getLocation().getId())
                                .name(user.getLocation().getName())
                                .latitude(user.getLocation().getLatitude())
                                .longitude(user.getLocation().getLongitude())
                                .build())
                        .build()).collect(Collectors.toList());
    }


    public GetUserDto getUserById(Long id) {
        return mapUserToGetUserDto(id);
    }

    private GetUserDto mapUserToGetUserDto(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserDoesNotExistException("User with " + id + " does not exist"));
        return GetUserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .surname(user.getSurname())
                .locationInfoDto(LocationInfoDto.builder()
                        .name(user.getLocation().getName())
                        .id(user.getLocation().getId())
                        .longitude(user.getLocation().getLongitude())
                        .latitude(user.getLocation().getLatitude())
                        .build())
                .build();

    }

    @Override
    public void editUser(Long id, EditUserDto dto) {
        User user = findUserById(id);
        user.setName(dto.getName() != null ? dto.getName() : user.getName());
        user.setSurname(dto.getSurname() != null ? dto.getSurname() : user.getSurname());
        user.setAge(dto.getAge() != null ? dto.getAge() : user.getAge());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(findUserById(id));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException("User with id: " + id + " does not exist"));
    }
}
