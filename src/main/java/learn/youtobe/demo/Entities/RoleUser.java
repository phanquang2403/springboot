package learn.youtobe.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class RoleUser {
    @Id
    @NotBlank
    @Column(name = "ROLE_USER_ID")
    private Integer ROLE_USER_ID;

    @NotBlank
    @Column(name = "ROLE_ID")
    private String ROLE_ID;

    @NotBlank
    @Column(name = "USER_ID")
    private Integer USER_ID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    private Date CREATED;

    @Column(name = "CREATE_BY")
    private Long CREATE_BY;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED")
    private Date UPDATED;

    @Column(name = "UPDATE_BY")
    private Long UPDATE_BY;
}
