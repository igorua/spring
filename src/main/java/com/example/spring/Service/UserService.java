package com.example.spring.Service;

import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.dto.GetUserDto;

import java.util.List;

public interface UserService {
    void addUser(CreateUserDto createUserDto);
    List<GetUserDto> getUser();
    GetUserDto getUserById(Long id);
    void editUser(Long id, EditUserDto dto);
    void deleteUser(Long id);
}
