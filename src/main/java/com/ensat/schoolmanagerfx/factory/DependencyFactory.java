package com.ensat.schoolmanagerfx.factory;

import com.ensat.schoolmanagerfx.dao.UtilisateurDao;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;
import com.ensat.schoolmanagerfx.service.AuthentificationService;
import lombok.Getter;

public class DependencyFactory {

    @Getter
    private static final UtilisateurDao utilisateurDao;
    @Getter
    private static final AuthentificationService authentificationService;

    static {
        // Initialisation des dépendances (utilisation d'un proxy pour DAO)
        utilisateurDao = Inject.init(UtilisateurDao.class);
        authentificationService = new AuthentificationService(utilisateurDao);
    }

}
