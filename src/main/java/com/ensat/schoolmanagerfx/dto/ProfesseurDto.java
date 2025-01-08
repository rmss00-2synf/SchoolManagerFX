package com.ensat.schoolmanagerfx.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfesseurDto {
    private int id;
    private String nom;
    private String prenom;
    private String specialite;
    private String username;
    private String role;
}
