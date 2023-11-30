package learn.youtobe.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class ModuleRole {

    @Id
    @NotBlank
    @Column(name = "ID_MODULE_ROLE")
    private Long ID_MODULE_ROLE;

    @Column(name = "ID_MODULE")
    private Long ID_MODULE;

    @NotBlank
    @Column(name = "ROLE_NAME")
    private String ROLE_NAME;

    @NotBlank
    @Column(name = "ROLE_CODE")
    private String ROLE_CODE;

    @NotBlank
    @Column(name = "DOC_TYPE")
    private Long DOC_TYPE;

    @Column(name = "DOC_STATUS")
    private Long DOC_STATUS;


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
