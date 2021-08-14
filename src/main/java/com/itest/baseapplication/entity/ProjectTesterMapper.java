package com.itest.baseapplication.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_tester_mapper")
@Setter
@Getter
public class ProjectTesterMapper {
    
	@Id
    private Integer projectTesterMapperId;

    private String projectId;

    private Integer testerId;
}
