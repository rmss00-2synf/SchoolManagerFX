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
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfService {
    private final EtudiantDao etudiantDao;
    private final ProfesseurDao professeurDao;
    private final InscriptionDao inscriptionDao;
    private final EtudiantService etudiantService = new EtudiantService();
    private final InscriptionService inscriptionService = new InscriptionService();
    private final ProfesseurService professeurService = new ProfesseurService();

    public PdfService() {
        this.etudiantDao= Inject.init(EtudiantDao.class);
        this.professeurDao = Inject.init(ProfesseurDao.class);
        this.inscriptionDao = Inject.init(InscriptionDao.class);
    }

    public boolean genereLaListe(String entity){
        LocalTime now = LocalTime.now();


        switch (entity.toUpperCase()) {
            case "ETUDIANT": List<Etudiant> etudiants = (List<Etudiant>) etudiantDao.findAll(Etudiant.class);
            List<EtudiantDto> etudiantDtos = new ArrayList<>();
            for (Etudiant etudiant: etudiants){
                etudiantDtos.add(etudiantService.convertToDto(etudiant));
            }
            System.out.println(etudiantDtos);

            try {
                // Initialiser le PDFWriter et le document
                String dest = "src/main/resources/pdf/td/"+now.getNano()+"AdvancedExample.pdf";
                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                // Ajouter une image
                String imagePath = "src/main/resources/views/ensat.jpeg"; // Chemin de votre image
                ImageData imageData = ImageDataFactory.create(imagePath);
                Image image = new Image(imageData).scaleToFit(100, 100);
                document.add(image);

                // Ajouter un titre
                document.add(new Paragraph("RAPPORT DES ETUDIANTS AU SAINT DE L'ECOLE")
                        .setFontSize(20)
                        .setBold()
                        .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));
                System.out.println("C'est bon encore");


                // Ajouter un tableau
                Field[] fields = etudiantDtos.getFirst().getClass().getDeclaredFields();
                float[] floats = new float[fields.length];
                System.out.println(fields.length);
                Arrays.fill(floats, 1);

                Table table = new Table(UnitValue.createPercentArray(floats))
                        .setWidth(UnitValue.createPercentValue(100));
                for (Field field : fields) {
                    System.out.println(field.getName());
                    table.addHeaderCell(new Cell().add(new Paragraph(field.getName().toUpperCase())));
                }
                for(EtudiantDto etudiantDto: etudiantDtos){

                    table.addCell(String.valueOf(etudiantDto.getId()));
                    table.addCell(etudiantDto.getMatricule());
                    table.addCell(String.valueOf(etudiantDto.getDate_naissance()));
                    table.addCell(etudiantDto.getEmail());
                    table.addCell(String.valueOf(etudiantDto.getPromotion()));
                    table.addCell(etudiantDto.getNom());
                    table.addCell(etudiantDto.getPrenom());
                }
                document.add(table);
                document.close();
                System.out.println("PDF généré avec succès : " + dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
                return true;

            case "PROFESSEUR": List<Professeur> professeurs = (List<Professeur>) professeurDao.findAll(Professeur.class);

            List<ProfesseurDto> professeurDtos = new ArrayList<>();
                for (Professeur professeur: professeurs){
                    professeurDtos.add(professeurService.convertToDto(professeur));
                }
                System.out.println(professeurDtos);

                try {
                    // Initialiser le PDFWriter et le document
                    String dest = "src/main/resources/pdf/prof/"+now.getNano()+"AdvancedExample.pdf";
                    PdfWriter writer = new PdfWriter(dest);
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document document = new Document(pdfDoc);

                    // Ajouter une image
                    String imagePath = "src/main/resources/views/ensat.jpeg"; // Chemin de votre image
                    ImageData imageData = ImageDataFactory.create(imagePath);
                    Image image = new Image(imageData).scaleToFit(100, 100);
                    document.add(image);

                    // Ajouter un titre
                    document.add(new Paragraph("RAPPORT DES PROFESSEURS AU SAINT DE L'ECOLE")
                            .setFontSize(20)
                            .setBold()
                            .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));
                    System.out.println("C'est bon encore");


                    // Ajouter un tableau
                    Field[] fields = professeurDtos.getFirst().getClass().getDeclaredFields();
                    float[] floats = new float[fields.length];
                    System.out.println(fields.length);
                    Arrays.fill(floats, 1);

                    Table table = new Table(UnitValue.createPercentArray(floats))
                            .setWidth(UnitValue.createPercentValue(100));
                    for (Field field : fields) {
                        System.out.println(field.getName());
                        table.addHeaderCell(new Cell().add(new Paragraph(field.getName().toUpperCase())));
                    }
                    for(ProfesseurDto professeurDto: professeurDtos){
                        table.addCell(String.valueOf(professeurDto.getId()));
                        table.addCell(professeurDto.getNom());
                        table.addCell(professeurDto.getPrenom());
                        table.addCell(professeurDto.getSpecialite());
                        table.addCell(professeurDto.getUsername());
                        table.addCell(professeurDto.getRole());
                    }
                    document.add(table);
                    document.close();
                    System.out.println("PDF généré avec succès : " + dest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;





            case "INSCRIPTION": List<Inscription> inscriptions = (List<Inscription>) inscriptionDao.findAll(Inscription.class);
                List<InscriptionDto> inscriptionDtos = new ArrayList<>();
                for (Inscription inscription: inscriptions){
                    inscriptionDtos.add(inscriptionService.inscriptionDto(inscription));
                }
                System.out.println(inscriptionDtos);

                try {
                    // Initialiser le PDFWriter et le document
                    String dest = "src/main/resources/pdf/inscrit/"+now.getNano()+"AdvancedExample.pdf";
                    PdfWriter writer = new PdfWriter(dest);
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document document = new Document(pdfDoc);

                    // Ajouter une image
                    String imagePath = "src/main/resources/views/ensat.jpeg"; // Chemin de votre image
                    ImageData imageData = ImageDataFactory.create(imagePath);
                    Image image = new Image(imageData).scaleToFit(100, 100);
                    document.add(image);

                    // Ajouter un titre
                    document.add(new Paragraph("RAPPORT DES INSCRIPTIONS AU SAINT DE L'ECOLE")
                            .setFontSize(20)
                            .setBold()
                            .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));
                    System.out.println("C'est bon encore");


                    // Ajouter un tableau
                    Field[] fields = inscriptionDtos.getFirst().getClass().getDeclaredFields();
                    float[] floats = new float[fields.length];
                    System.out.println(fields.length);
                    Arrays.fill(floats, 1);

                    Table table = new Table(UnitValue.createPercentArray(floats))
                            .setWidth(UnitValue.createPercentValue(100));
                    for (Field field : fields) {
                        System.out.println(field.getName());
                        table.addHeaderCell(new Cell().add(new Paragraph(field.getName().toUpperCase())));
                    }
                    for(InscriptionDto inscriptionDto: inscriptionDtos){
                        table.addCell(String.valueOf(inscriptionDto.getId()));
                        table.addCell(String.valueOf(inscriptionDto.getDate_inscription()));
                        table.addCell(String.valueOf(inscriptionDto.getId_etudiant()));
                        table.addCell(String.valueOf(inscriptionDto.getId_module()));
                    }

                    document.add(table);
                    document.close();
                    System.out.println("PDF généré avec succès : " + dest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            default: return false;
        }
    }


    public static void main(String[] args) {
        PdfService pdfService = new PdfService();
        System.out.println(pdfService.genereLaListe("INSCRIPTION"));
    }

}
