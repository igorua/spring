package com.example.spring.service;

import com.example.spring.ServiceImpl.UserServiceImpl;
import com.example.spring.dao.Entity.Location;
import com.example.spring.dao.Entity.User;
import com.example.spring.dao.Repository.LocationRepository;
import com.example.spring.dao.Repository.UserRepository;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.dto.GetUserDto;
import com.example.spring.exception.LocationDoesNotExistException;
import com.example.spring.exception.UserDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    LocationRepository locationRepository;
    @Mock
    UserRepository userRepository;

    @Test
    void addUser() {
        CreateUserDto dto = Models.getCreateUserDto();
        Location location = Models.locationForCreateUser();
        User user = Models.getUser();

        when(locationRepository.findById(dto.getLocationId())).thenReturn(Optional.ofNullable(location));
        when(userRepository.save(user)).thenReturn(user);

        userService.addUser(dto);

        verify(locationRepository).findById(dto.getLocationId());
        verify(userRepository).save(user);
    }

    @Test
    void addUserIfLocationDoesntExist() {
        CreateUserDto dto = Models.getCreateUserDto();

        when(locationRepository.findById(dto.getLocationId())).thenReturn(Optional.empty());

        assertThrows(LocationDoesNotExistException.class, () -> userService.addUser(dto));

        verify(locationRepository).findById(dto.getLocationId());
    }

    @Test
    void getALlUsers() {
        GetUserDto dto = Models.allUsers();
        User user = Models.getUser();
        List<GetUserDto> dtos = new ArrayList<>();
        dtos.add(dto);
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(dtos, userService.getUser());
    }

    @Test
    void getUserByID() {
        GetUserDto dto = Models.allUsers();
        User user = Models.getUser();

        when(userRepository.findById(dto.getId())).thenReturn(Optional.of(user));

        assertEquals(dto, userService.getUserById(user.getId()));

        verify(userRepository).findById(dto.getId());
    }

    @Test
    void getUserByIDThrowsException() {
        GetUserDto dto = Models.allUsers();
        User user = Models.getUser();

        when(userRepository.findById(dto.getId())).thenReturn(Optional.empty());

        assertThrows(UserDoesNotExistException.class, () -> userService.getUserById(user.getId()));

        verify(userRepository).findById(dto.getId());
    }

    @Test
    void editUser() {
        User user = Models.getUser();
        EditUserDto dto = Models.getEditUserDto();
        User edited = User.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .surname(dto.getSurname())
                .location(Models.locationForCreateUser())
                .build();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(edited);

        userService.editUser(user.getId(), dto);

        verify(userRepository).findById(user.getId());
        verify(userRepository).save(edited);
    }

    @Test
    void editUserWithoutDataFromDto() {
        User user = Models.getUser();
        EditUserDto dto = new EditUserDto();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        userService.editUser(user.getId(), dto);

        verify(userRepository).findById(user.getId());
        verify(userRepository).save(user);
    }

    @Test
    void editUserThrowsException() {
        User user = Models.getUser();
        EditUserDto dto = Models.getEditUserDto();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserDoesNotExistException.class,()->userService.editUser(user.getId(), dto));

        verify(userRepository).findById(user.getId());
    }

    @Test
    void deleteUser(){
        User user = Models.getUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getId());

        verify(userRepository).findById(user.getId());
        verify(userRepository).delete(user);
    }
}
