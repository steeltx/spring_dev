package com.portfolio.portfolio_backend.service;

import com.portfolio.portfolio_backend.model.Project;

import java.util.List;

public interface IProjectService {

    List<Project> findAll();
    Project save(Project project);

}
