package com.jobportal.demo.job_portal.controller;
import com.jobportal.demo.job_portal.dto.LoginDTO;
import com.jobportal.demo.job_portal.dto.ResponseDTO;
import com.jobportal.demo.job_portal.dto.UserDTO;
import com.jobportal.demo.job_portal.exception.JobPortalException;
import com.jobportal.demo.job_portal.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Validated
public class UserAPI
{
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity <UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/create") // New endpoint for creating a user
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
@PostMapping("/login")
    public ResponseEntity <UserDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {

        return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity <ResponseDTO> sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email) throws Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("OTP send Successfully"), HttpStatus.OK);
    }
    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity <ResponseDTO> verifyOtp(@PathVariable @Email(message = "{user.email.invalid}") String email,@PathVariable @Pattern(regexp = "[0-9]{6}$",message = "{otp.invalid}") String otp) throws JobPortalException {
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDTO("OTP verified Successfully"), HttpStatus.OK);
    }

}
