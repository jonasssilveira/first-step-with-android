package com.barbershop.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluno implements Serializable {
    private String nome;
    private String telefone;
    private String email;
    private String id;

    @Override
    public String toString() {
        return nome;
    }

}
