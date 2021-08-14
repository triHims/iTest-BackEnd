package com.itest.baseapplication.service;


import com.itest.baseapplication.Auth.AuthenticatedProfileHelper;
import com.itest.baseapplication.dto.DeveloperDTO;
import com.itest.baseapplication.dto.ProfileDTO;
import com.itest.baseapplication.dto.ProjectDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.entity.Developer;
import com.itest.baseapplication.entity.Project;
import com.itest.baseapplication.entity.Task;
import com.itest.baseapplication.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectServiceImpl  implements  ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmpRecordsRepo empRecords;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private DeveloperRepo developerRepo;
    @Autowired
    private TesterRepo testerRepo;

    @Autowired
    private ProjectDeveloperMapperRepo projectDeveloperMapperRepo;

    @Autowired
    private ProjectTesterMapperRepo projectTesterMapperRepo;
    
    @Override
    public ProjectDTO getProject(String projectId)  {
        log.info(String.format("Called %s from class %s", "getProject","ProjectServiceImpl" ));
        Optional<Project> projectOption = projectRepository.findById(projectId);
        if(projectOption.isPresent())
        {
        	ProjectDTO project =  ProjectDTO.dtoFromEntity(projectOption.get());
        	//find the developer
        	Developer developer = developerRepo.findByDeveloperId(Long.parseLong(project.getLeadDeveloperId()));
        	project.setCreatedBy(DeveloperDTO.getDTOFromEntity(developer));
        	//find total tasks
        	List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
        	List<Task> tasks = taskRepo.findByTaskCreatorAndProjectId(developer.getDeveloperId().toString(), project.getId());
        	tasks.stream().forEach(task -> tasksDTO.add(TaskDTO.dtofromObject(task)));
        	project.setTasks(tasksDTO);
        	// show tasks attempted to tester
        	return project;        	
        	
        }
        log.info(String.format("Exiting from %s 0f class %s", "getProject","ProjectServiceImpl" ));
        return new ProjectDTO();
    }


    public List <ProjectDTO> getAllProjects() {
        log.info(String.format("Inside %s from class %s", "getProject","ProjectServiceImpl" ));

        ProfileDTO  profile = new AuthenticatedProfileHelper().getProfile();




        if(profile.getRole().equals("admin"))
            return projectRepository.findAll().stream().map(ProjectDTO::dtoFromEntity).collect(Collectors.toList());

        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        List<String> allocatedProjectList =new ArrayList <>();
        List<Project> allProjects=null;




        if(profile.getRole().equals("developer")) {
            allocatedProjectList = projectDeveloperMapperRepo
                    .findProjectByDeveloperId(Integer.parseInt(profile.getUserId()));
        }else  if(profile.getRole().equals("tester")) {
            allocatedProjectList = projectTesterMapperRepo
                    .findProjectByTesterId(Integer.parseInt(profile.getUserId()));
        }




            allProjects = projectRepository.findAllByIdIn(allocatedProjectList);

        	projects = allProjects.stream().map(ProjectDTO::dtoFromEntity).collect(Collectors.toList());

        	return projects;
    }
}
