package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.dao.EtudiantDao;
import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.sql.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // Initialisation du DAO
        EtudiantDao etudiantDao = Inject.init(EtudiantDao.class);

        // Test 1 : Ajouter un étudiant
        System.out.println("=== Test save() ===");
        Etudiant etudiant = new Etudiant(
                "ENSA12345",
                new Date(System.currentTimeMillis()),
                "test@ensat.ma",
                2026,
                null,
                null
        );
        etudiantDao.save(etudiant);
        System.out.println("Étudiant ajouté : " + etudiant);

        // Test 2 : Récupérer un étudiant par ID
        System.out.println("=== Test findById() ===");
        etudiantDao.findById(Etudiant.class, etudiant.getId()).ifPresentOrElse(
                e -> System.out.println("Étudiant trouvé : " + e),
                () -> System.out.println("Aucun étudiant trouvé.")
        );

        // Test 3 : Rechercher par nom
        System.out.println("=== Test findByNom() ===");
        List<Etudiant> etudiantsParNom = etudiantDao.findByNom("Test");
        etudiantsParNom.forEach(e -> System.out.println("Trouvé : " + e));

        // Test 4 : Rechercher par promotion
        System.out.println("=== Test findByPromotion() ===");
        List<Etudiant> etudiantsParPromotion = etudiantDao.findByPromotion("2026");
        etudiantsParPromotion.forEach(e -> System.out.println("Promotion : " + e));

        // Test 5 : Rechercher les modules d'un étudiant
        System.out.println("=== Test findModulesByEtudiant() ===");
        int etudiantId = etudiant.getId().intValue(); // Utilise l'ID réel ajouté
        List<Module> modules = etudiantDao.findModulesByEtudiant(etudiantId);
        modules.forEach(m -> System.out.println("Module : " + m));

        // Test 6 : Récupérer tous les étudiants
        System.out.println("=== Test getAll() ===");
        List<Etudiant> allEtudiants = etudiantDao.getAll(Etudiant.class);
        allEtudiants.forEach(System.out::println);
    }
}
