package learn.youtobe.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class Accounts {
    @Id
    @NotBlank
    @Column(name = "USER_ID")
    private Integer USER_ID;

    @NotBlank
    @Column(name = "USERNAME")
    private String USERNAME;



    @NotBlank
    @Column(name = "LAST_NAME")
    private String LAST_NAME;


    @NotBlank
    @Column(name = "FIRST_NAME")
    private String FIRST_NAME;

    @NotBlank
    @Column(name = "PHONE_NUMBER")
    private String PHONE_NUMBER;


    @NotBlank
    @Column(name = "ADDRESS")
    private String ADDRESS;

    @NotBlank
    @Column(name = "EMAIL")
    private String EMAIL;


    @NotBlank
    @Column(name = "CCCD")
    private String CCCD;


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
