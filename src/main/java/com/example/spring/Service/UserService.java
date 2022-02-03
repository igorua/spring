package com.example.spring.Service;

import dto.CreateUserDto;
import dto.EditUserDto;
import dto.GetUserDto;

import java.util.List;

public interface UserService {
    void addUser(CreateUserDto createUserDto);
    List<GetUserDto> getUser();
    GetUserDto getUserById(Long id);
    void editUser(Long id, EditUserDto dto);
    void deleteUser(Long id);
}
