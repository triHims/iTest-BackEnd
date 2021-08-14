package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.EmpRecordsDTO;
import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.dto.SignUpDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.exception.ProfileNotFoundException;

public interface UserProfileService {
    EmpRecordsDTO getUserProfile( String userName) throws ProfileNotFoundException;
}
