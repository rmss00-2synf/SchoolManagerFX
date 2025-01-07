package com.ensat.schoolmanagerfx.dto;

import com.ensat.schoolmanagerfx.entity.Inscription;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EtudiantDto {
        private Long id;
        private String matricule;
        private Date date_naissance;
        private String email;
        private Long promation;
        private Inscription inscription;
}
