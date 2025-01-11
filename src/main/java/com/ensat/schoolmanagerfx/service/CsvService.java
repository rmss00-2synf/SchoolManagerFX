package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.EtudiantDao;
import com.ensat.schoolmanagerfx.dao.InscriptionDao;
import com.ensat.schoolmanagerfx.dao.ProfesseurDao;
import com.ensat.schoolmanagerfx.dto.EtudiantDto;
import com.ensat.schoolmanagerfx.dto.InscriptionDto;
import com.ensat.schoolmanagerfx.dto.ProfesseurDto;
import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.entity.Inscription;
import com.ensat.schoolmanagerfx.entity.Professeur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CsvService {
    private final EtudiantDao etudiantDao;
    private final ProfesseurDao professeurDao;
    private final InscriptionDao inscriptionDao;
    private final EtudiantService etudiantService = new EtudiantService();
    private final InscriptionService inscriptionService = new InscriptionService();
    private final ProfesseurService professeurService = new ProfesseurService();

    public CsvService() {
        this.etudiantDao = Inject.init(EtudiantDao.class);
        this.professeurDao = Inject.init(ProfesseurDao.class);
        this.inscriptionDao = Inject.init(InscriptionDao.class);
    }

    private void generateCsv(List<?> items, String entity, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            if (!items.isEmpty()) {
                addHeaders(writer, items.get(0));
                for (Object item : items) {
                    addRow(writer, item);
                }
            }

            System.out.println("CSV généré avec succès : " + fileName);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void addHeaders(FileWriter writer, Object item) throws IOException {
        Field[] fields = item.getClass().getDeclaredFields();
        StringBuilder header = new StringBuilder();

        for (Field field : fields) {
            header.append(field.getName().toUpperCase()).append(",");
        }

        writer.append(header.substring(0, header.length() - 1)).append("\n");
    }

    private void addRow(FileWriter writer, Object item) throws IOException, IllegalAccessException {
        Field[] fields = item.getClass().getDeclaredFields();
        StringBuilder row = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            row.append(field.get(item)).append(",");
        }

        writer.append(row.substring(0, row.length() - 1)).append("\n");
    }

    public boolean genereLaListe(String entity) {
        LocalTime now = LocalTime.now();
        String fileName = "src/main/resources/csv/" + entity.toLowerCase() + "/" + now.getNano() + ".csv";

        switch (entity.toUpperCase()) {
            case "ETUDIANT":
                List<Etudiant> etudiants = (List<Etudiant>) etudiantDao.findAll(Etudiant.class);
                List<EtudiantDto> etudiantDtos = new ArrayList<>();
                for (Etudiant etudiant : etudiants) {
                    etudiantDtos.add(etudiantService.convertToDto(etudiant));
                }
                generateCsv(etudiantDtos, "ETUDIANTS", fileName);
                return true;

            case "PROFESSEUR":
                List<Professeur> professeurs = (List<Professeur>) professeurDao.findAll(Professeur.class);
                List<ProfesseurDto> professeurDtos = new ArrayList<>();
                for (Professeur professeur : professeurs) {
                    professeurDtos.add(professeurService.convertToDto(professeur));
                }
                generateCsv(professeurDtos, "PROFESSEURS", fileName);
                return true;

            case "INSCRIPTION":
                List<Inscription> inscriptions = (List<Inscription>) inscriptionDao.findAll(Inscription.class);
                List<InscriptionDto> inscriptionDtos = new ArrayList<>();
                for (Inscription inscription : inscriptions) {
                    inscriptionDtos.add(inscriptionService.inscriptionDto(inscription));
                }
                generateCsv(inscriptionDtos, "INSCRIPTIONS", fileName);
                return true;

            default:
                return false;
        }
    }

    public static void main(String[] args) {
        CsvService csvService = new CsvService();
        System.out.println(csvService.genereLaListe("INSCRIPTION"));
    }
}
