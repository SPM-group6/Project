package com.hwadee.core.repository;

import com.hwadee.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository {
    List<Project> queryProjects();
    List<Project> queryAlterProjects();
}
