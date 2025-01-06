package com.ensat.schoolmanagerfx.utils.ensatjpa.config;

public enum CascadeType {
    ALL,        // Applique toutes les actions de cascade.
    PERSIST,    // Propagation lors de la persistance.
    MERGE,      // Propagation lors de la fusion.
    REMOVE,     // Propagation lors de la suppression.
    REFRESH,    // Propagation lors de la mise à jour.
    DETACH      // Propagation lors de la détachement.
}

