package api.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@Entity
@Table(name = "portfolio_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Project> projects;

    @OneToMany(mappedBy = "user")
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "user")
    private Set<Education> educations;

    @OneToMany(mappedBy = "user")
    private Set<Skill> skills;

    @OneToMany(mappedBy = "user")
    private Set<Language> languages;

    @OneToMany(mappedBy = "user")
    private Set<Certificate> certificates;

    @OneToOne
    private Contact contact;

    @ManyToMany(mappedBy = "users")
    private List<Portfolio> portfolios;
}