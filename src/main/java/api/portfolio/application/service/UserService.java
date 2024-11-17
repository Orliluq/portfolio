package api.portfolio.application.service;

import api.portfolio.application.dto.UserDTO;
import api.portfolio.application.mapper.PortfolioMapper;
import api.portfolio.domain.model.User;
import api.portfolio.infrastructure.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PortfolioMapper portfolioMapper;
    @Autowired
    public UserService(UserRepository userRepository, PortfolioMapper portfolioMapper) {
        this.userRepository = userRepository;
        this.portfolioMapper = portfolioMapper;
    }

    public Optional<UserDTO> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(PortfolioMapper.INSTANCE::userToUserDTO);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(portfolioMapper::userToUserDTO)
                .toList();
    }

    public UserDTO createUser(@Valid UserDTO userDTO) {
        userDTO.setId(null);

        User user = portfolioMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return portfolioMapper.userToUserDTO(savedUser);
    }

    public Optional<UserDTO> updateUser(Long userId, UserDTO userDTO) {
        return userRepository.findById(userId).map(existingUser -> {

            existingUser.setName(userDTO.getName());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setProjects(userDTO.getProjects().stream()
                    .map(portfolioMapper::projectDTOToProject)
                    .collect(Collectors.toSet()));
            existingUser.setExperiences(userDTO.getExperiences().stream()
                    .map(portfolioMapper::experienceDTOToExperience)
                    .collect(Collectors.toSet()));
            existingUser.setEducations(userDTO.getEducations().stream()
                    .map(portfolioMapper::educationDTOToEducation)
                    .collect(Collectors.toSet()));
            existingUser.setSkills(userDTO.getSkills().stream()
                    .map(portfolioMapper::skillDTOToSkill)
                    .collect(Collectors.toSet()));
            existingUser.setLanguages(userDTO.getLanguages().stream()
                    .map(portfolioMapper::languageDTOToLanguage)
                    .collect(Collectors.toSet()));
            existingUser.setCertificates(userDTO.getCertificates().stream()
                    .map(portfolioMapper::certificateDTOToCertificate)
                    .collect(Collectors.toSet()));
            existingUser.setContact(portfolioMapper.contactDTOToContact(userDTO.getContact()));

            User updatedUser = userRepository.save(existingUser);
            return portfolioMapper.userToUserDTO(updatedUser);
        });
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}