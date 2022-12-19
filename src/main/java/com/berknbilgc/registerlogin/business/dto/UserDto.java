package com.berknbilgc.registerlogin.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String password;
    // private LocalDateTime createdDate;
    private Date createdDate;
}