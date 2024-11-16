package api.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @OneToOne(mappedBy = "contact")
    private User user;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}