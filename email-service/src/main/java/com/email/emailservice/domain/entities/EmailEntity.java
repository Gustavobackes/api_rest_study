package com.email.emailservice.domain.entities;


import com.email.emailservice.domain.enums.StatusEmail;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "m_email")
public class EmailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_email")
    private UUID emailId;

    @Column(name = "owner_ref")
    private String ownerRef;

    @NotNull
    @Column(name = "email_from")
    private String emailFrom;

    @NotNull
    @Column(name = "email_to")
    private String emailTo;

    @Column(name = "subject")
    private String subject;

    @Column(columnDefinition = "TEXT", name = "text")
    private String text;

    @NotNull
    @Column(name = "send_date_email")
    private LocalDateTime sendDateEmail;

    @NotNull
    @Column(name = "status_email")
    private StatusEmail statusEmail;


}
