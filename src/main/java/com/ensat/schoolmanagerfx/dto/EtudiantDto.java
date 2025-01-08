package com.ensat.schoolmanagerfx.dto;

import com.ensat.schoolmanagerfx.entity.Inscription;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EtudiantDto {
        private int id;
        private String matricule;
        private Date date_naissance;
        private String email;
        private int promotion;
        private String nom;
        private String prenom;
}
