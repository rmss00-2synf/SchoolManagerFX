package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.dao.*;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
        EtudiantDao etudiantDao = Inject.init(EtudiantDao.class);
//        test(Etudiant.class);
//        etudiantDao.getAll(Etudiant.class);
        System.out.println(etudiantDao.findByMatricule("ENSA12345",new Etudiant()).get());
//        System.out.println(etudiantDao.findById(Etudiant.class,3L).get());
//        java.util.Date utilDate = new java.util.Date();
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

//        Etudiant etudiant = new Etudiant("ENSA14445782",sqlDate,"Y@FA",2025,null, new Personne(20,"Sylla","N'faly"));
//        etudiantDao.save(etudiant);
        //        ModuleDao moduleDao = Inject.init(ModuleDao.class);
//        System.out.println(moduleDao.getAll(Module.class));
//        InscriptionDao inscriptionDao = Inject.init(InscriptionDao.class);
//        System.out.println(inscriptionDao.getAll(Inscription.class));
//        ProfesseurDao personneDao = Inject.init(ProfesseurDao.class);
//        System.out.println(personneDao.findAll(Professeur.class));
//        UtilisateurDao utilisateurDao = Inject.init(UtilisateurDao.class);
//        System.out.println(utilisateurDao.findAll(Utilisateur.class));

    }

    public static void test(Class<?> clazz) {
        System.out.println(clazz.getSimpleName());
    }
}
