package com.example.spring.ServiceImpl;

import com.example.spring.Service.UserService;
import com.example.spring.dao.Entity.User;
import com.example.spring.dao.Repository.UserRepository;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.dto.GetUserDto;
import com.example.spring.dto.LocationInfoDto;
import com.example.spring.exception.UserDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
        return user;
    }

    @Override
    public List<GetUserDto> getUser() {
        List<User> users = userRepository.findAll();
        List<GetUserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(getUserDto(user)));
        return userDtos;
    }

    private GetUserDto getUserDto(User user) {
        return GetUserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .surname(user.getSurname())
                .locationInfoDtoList(user.getLocations().stream()
                        .map(location -> new LocationInfoDto(
                                location.getId(), location.getName(),
                                location.getLongitude(), location.getLatitude()))
                        .collect(Collectors.toList())).build();
    }

    public GetUserDto getUserById(Long id) {
        return getUserDto(findUserById(id));
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
