package com.ensat.schoolmanagerfx.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModuleDto {
    private int id;
    private String nom_module;
    private String code_module;
    private int id_professeur;
}
