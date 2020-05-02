package com.bw.foodvendor.service;

import com.bw.foodvendor.dto.LoginRequestDto;
import com.bw.foodvendor.dto.RegisterRequestDto;
import com.bw.foodvendor.entity.PortalUser;


public interface UserManagementService {

    public Object login (LoginRequestDto loginRequestDto);

    public PortalUser register(RegisterRequestDto registerRequestDto);
}
