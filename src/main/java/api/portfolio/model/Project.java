package api.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name cannot be blank")
    private String projectName;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private String url;

    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}