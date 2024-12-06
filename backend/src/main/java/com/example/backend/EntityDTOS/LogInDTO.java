package com.example.backend.EntityDTOS;

import com.example.backend.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LogInDTO {


    private Integer id ;
    private Role role ;


}
