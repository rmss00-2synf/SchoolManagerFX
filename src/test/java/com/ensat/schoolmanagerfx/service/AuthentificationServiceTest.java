//package com.ensat.schoolmanagerfx.service;
//
//import com.ensat.schoolmanagerfx.dao.UtilisateurDao;
//import com.ensat.schoolmanagerfx.entity.Utilisateur;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AuthentificationServiceTest {
//
//    @Test
//    void testAuthentifier_UtilisateurValide() {
//        // Mock du DAO
//        UtilisateurDao mockDao = mock(UtilisateurDao.class);
//
//        // Création d'un utilisateur simulé
//        Utilisateur utilisateurSimule = new Utilisateur();
//        utilisateurSimule.setUsername("testUser");
//        utilisateurSimule.setPassword("testPassword");
//        utilisateurSimule.setRole("admin");
//
//        // Simuler le comportement du DAO
////        when(mockDao.findByAttribute(anyString(),eq(Utilisateur.class)))
////                .thenReturn((Optional) Optional.of(utilisateurSimule));
////
////        // Instancier le service avec le mock
////        AuthentificationService service = new AuthentificationService(mockDao);
//
//        // Appeler la méthode à tester
////        Optional<Utilisateur> resultat = service.authentifier("testUser", "testPassword");
//
//        // Vérifier les résultats
////        assertTrue(resultat.isPresent());
////        assertEquals("testUser", resultat.get().getUsername());
////        assertEquals("admin", resultat.get().getRole());
//    }
//
//    @Test
//    void testAuthentifier_UtilisateurInvalide() {
//        // Mock du DAO
//        UtilisateurDao mockDao = mock(UtilisateurDao.class);
//
////        // Simuler le comportement du DAO pour un utilisateur non trouvé
////        when(mockDao.findByAttribute("SELECT * FROM Utilisateur WHERE username = 'wrongUser' AND password = 'wrongPassword'",Utilisateur.class))
////                .thenReturn(Optional.empty());
//
//        // Instancier le service avec le mock
//        AuthentificationService service = new AuthentificationService(mockDao);
//
//        // Appeler la méthode à tester
//        Optional<Utilisateur> resultat = service.authentifier("wrongUser", "wrongPassword");
//
//        // Vérifier les résultats
//        assertFalse(resultat.isPresent());
//    }
//}
