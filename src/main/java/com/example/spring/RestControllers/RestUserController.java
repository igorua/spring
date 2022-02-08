package com.example.spring.RestControllers;

import com.example.spring.Service.UserService;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.dto.GetUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/user")
public class RestUserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createUser(@RequestBody CreateUserDto dto){
        userService.addUser(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetUserDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDto> getOneUser(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") Long id,@RequestBody EditUserDto dto){
        userService.editUser(id,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable ("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
