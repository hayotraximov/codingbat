package uz.raximov.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.raximov.codingbat.entity.User;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.UserDto;
import uz.raximov.codingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<User> userList = userService.getAll();
        return ResponseEntity.status(userList.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(userList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse userServiceById = userService.getById(id);
        return ResponseEntity.status(userServiceById.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(userServiceById);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.add(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody UserDto userDto, @PathVariable Integer id){
        ApiResponse apiResponse = userService.edit(userDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
