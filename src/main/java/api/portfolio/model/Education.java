package api.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Institution cannot be blank")
    private String institution;

    @NotBlank(message = "Degree cannot be blank")
    private String degree;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    private Date endDate;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}