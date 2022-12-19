package com.berknbilgc.registerlogin.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

//Entity
@Entity
@Table(name="users")
public class UserEntity implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",length = 100,nullable = false)
    private String username;
    private String name;
    private String password;

    @Column(name = "created_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate; //=new Date(System.currentTimeMillis());
}
