package com.jobportal.demo.job_portal.service;

import com.jobportal.demo.job_portal.dto.LoginDTO;
import com.jobportal.demo.job_portal.dto.ResponseDTO;
import com.jobportal.demo.job_portal.dto.UserDTO;
import com.jobportal.demo.job_portal.exception.JobPortalException;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO) throws JobPortalException;


    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;


    public void sendOtp(String email)throws Exception;

    public Boolean verifyOtp(String email, String otp)throws JobPortalException;

    ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException;
}

