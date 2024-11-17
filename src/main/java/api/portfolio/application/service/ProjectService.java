package api.portfolio.application.service;

import api.portfolio.application.dto.ProjectDTO;
import api.portfolio.application.mapper.PortfolioMapper;
import api.portfolio.domain.model.Project;
import api.portfolio.infrastructure.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private static final PortfolioMapper portfolioMapper = PortfolioMapper.INSTANCE;

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(portfolioMapper::projectToProjectDTO)
                .toList();
    }

    public Optional<ProjectDTO> getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .map(portfolioMapper::projectToProjectDTO);
    }

    public ProjectDTO createProject(@Valid ProjectDTO projectDTO) {
        Project project = portfolioMapper.projectDTOToProject(projectDTO);
        Project savedProject = projectRepository.save(project);
        return portfolioMapper.projectToProjectDTO(savedProject);
    }

    public Optional<ProjectDTO> updateProject(Long projectId, ProjectDTO projectDTO) {
        return projectRepository.findById(projectId).map(existingProject -> {
            existingProject.setProjectName(projectDTO.getProjectName());
            existingProject.setDescription(projectDTO.getDescription());
            existingProject.setUrl(projectDTO.getUrl());
            existingProject.setImage(projectDTO.getImage());
            Project updatedProject = projectRepository.save(existingProject);
            return portfolioMapper.projectToProjectDTO(updatedProject);
        });
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}