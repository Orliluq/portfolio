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
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company cannot be blank")
    private String company;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    private Date endDate;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}