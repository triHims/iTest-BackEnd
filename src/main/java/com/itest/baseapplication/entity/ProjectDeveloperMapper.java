package com.itest.baseapplication.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_developer_mapper")
@Setter
@Getter
public class ProjectDeveloperMapper {
    
	@Id
    private Integer projectDeveloperMapperId;

    private String projectId;

    private Integer developerId;
}
