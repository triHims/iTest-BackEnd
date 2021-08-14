package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.EmpRecordsDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.exception.ProfileNotFoundException;
import com.itest.baseapplication.repository.EmpRecordsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private EmpRecordsRepo empRecordsRepo;


    @Override
    public EmpRecordsDTO getUserProfile ( String userName ) throws ProfileNotFoundException {
        log.info(String.format("Inside of:: %s from class:: %s", "getuserProfile","UserProfileServiceImpl" ));
        Optional <EmpRecords> empRecord = empRecordsRepo.findByUsername(userName);
        if(!empRecord.isPresent())
            throw new ProfileNotFoundException(String.format("The profile of the userName %s does not exists", userName));
        return EmpRecordsDTO.getDTOFromEntity(empRecord.get());

    }
}
