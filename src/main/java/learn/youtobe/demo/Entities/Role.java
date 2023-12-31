package learn.youtobe.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;


@Entity
public class Role {

    @Id
    @NotBlank
    @Column(name = "ROLE_ID")
    private Integer ROLE_ID;

    @NotBlank
    @Column(name = "ROLE_CODE")
    private String ROLE_CODE;

    @NotBlank
    @Column(name = "ROLE_NAME")
    private String ROLE_NAME;

    @NotBlank
    @Column(name = "DESCRIPTION")
    private String DESCRIPTION;

    @NotBlank
    @Column(name = "STATUS")
    private Integer STATUS;

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
