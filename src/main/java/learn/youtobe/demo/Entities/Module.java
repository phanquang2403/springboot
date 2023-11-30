package learn.youtobe.demo.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class Module {
    @Id
    @NotBlank
    @SequenceGenerator(name = "module_seq", sequenceName = "module_seq", allocationSize = 1)
    @Column(name = "ID_MODULE")
    private Long ID_MODULE;

    @Column(name = "PARENT_ID")
    private Long PARENT_ID;

    @NotBlank
    @Column(name = "MODULE_NAME")
    private String MODULE_NAME;

    @NotBlank
    @Column(name = "MODULE_CODE")
    private String MODULE_CODE;

    @Column(name = "DOC_TYPE")
    private Long DOC_TYPE;

    @Column(name = "DOC_STATUS")
    private Long DOC_STATUS;
    @Column(name = "URL")
    private String URL;

    @Column(name = "APP_ID")
    private Long APP_ID;

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
