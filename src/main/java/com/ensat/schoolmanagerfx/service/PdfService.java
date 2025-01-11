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
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.property.UnitValue;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PdfService {
    private final EtudiantDao etudiantDao;
    private final ProfesseurDao professeurDao;
    private final InscriptionDao inscriptionDao;
    private final EtudiantService etudiantService = new EtudiantService();
    private final InscriptionService inscriptionService = new InscriptionService();
    private final ProfesseurService professeurService = new ProfesseurService();

    public PdfService() {
        this.etudiantDao = Inject.init(EtudiantDao.class);
        this.professeurDao = Inject.init(ProfesseurDao.class);
        this.inscriptionDao = Inject.init(InscriptionDao.class);
    }

    private void generatePdf(List<?> items, String entity, String fileName) {
        try {
            PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            addImage(document);
            addTitle(document, entity);
            addTable(document, items);
            document.close();
            System.out.println("PDF généré avec succès : " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addImage(Document document) throws MalformedURLException {
        String imagePath = "src/main/resources/views/ensat.jpeg"; // Chemin de votre image
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData).scaleToFit(100, 100);
        document.add(image);
    }

    private void addTitle(Document document, String entity) {
        String title = "RAPPORT DES " + entity.toUpperCase() + " AU SAINT DE L'ECOLE";
        document.add(new Paragraph(title)
                .setFontSize(20)
                .setBold()
                .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));
    }

    private void addTable(Document document, List<?> items) throws IllegalAccessException {
        if (items.isEmpty()) return;
        Object firstItem = items.getFirst();
        Field[] fields = firstItem.getClass().getDeclaredFields();
        float[] columnWidths = new float[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnWidths[i] = 1;
        }
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).setWidth(UnitValue.createPercentValue(100));
        for (Field field : fields) {
            table.addHeaderCell(new Cell().add(new Paragraph(field.getName().toUpperCase())));
        }

        for (Object item : items) {
            for (Field field : fields) {
                field.setAccessible(true);
                table.addCell(String.valueOf(field.get(item)));
            }
        }

        document.add(table);
    }

    public boolean genereLaListe(String entity) {
        LocalTime now = LocalTime.now();
        String fileName = "src/main/resources/pdf/" + entity.toLowerCase() + "/" + now.getNano() + ".pdf";

        switch (entity.toUpperCase()) {
            case "ETUDIANT":
                List<Etudiant> etudiants = (List<Etudiant>) etudiantDao.findAll(Etudiant.class);
                List<EtudiantDto> etudiantDtos = new ArrayList<>();
                for (Etudiant etudiant : etudiants) {
                    etudiantDtos.add(etudiantService.convertToDto(etudiant));
                }
                generatePdf(etudiantDtos, "ETUDIANTS", fileName);
                return true;

            case "PROFESSEUR":
                List<Professeur> professeurs = (List<Professeur>) professeurDao.findAll(Professeur.class);
                List<ProfesseurDto> professeurDtos = new ArrayList<>();
                for (Professeur professeur : professeurs) {
                    professeurDtos.add(professeurService.convertToDto(professeur));
                }
                generatePdf(professeurDtos, "PROFESSEURS", fileName);
                return true;

            case "INSCRIPTION":
                List<Inscription> inscriptions = (List<Inscription>) inscriptionDao.findAll(Inscription.class);
                List<InscriptionDto> inscriptionDtos = new ArrayList<>();
                for (Inscription inscription : inscriptions) {
                    inscriptionDtos.add(inscriptionService.inscriptionDto(inscription));
                }
                generatePdf(inscriptionDtos, "INSCRIPTIONS", fileName);
                return true;

            default:
                return false;
        }
    }

    public static void main(String[] args) {
        PdfService pdfService = new PdfService();
        System.out.println(pdfService.genereLaListe("INSCRIPTION"));
    }
}
