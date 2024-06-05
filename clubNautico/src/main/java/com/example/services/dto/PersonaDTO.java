package com.example.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

        private Integer id;
        private String nombreCompleto;
        private String dni;
        private String email;

    }

