package com.security.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

@ToString
@AllArgsConstructor//cria contrutor padrao
@NoArgsConstructor// cria contrutor vazio
@Getter
@Setter
@Entity
public class StudenteEnty extends AbstractEntity{
    @NotEmpty(message = "O campo nome do estudante é obrigatorio")
    private String name;

    @NotEmpty
    @Email
    private String email;
}
