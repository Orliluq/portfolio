package api.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "portfolio")
    private List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "portfolio")
    private List<Project> projects;

    @ManyToMany
    @JoinTable(
            name = "portfolio_users",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}