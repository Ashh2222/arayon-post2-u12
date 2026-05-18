package com.empresa.pedidos;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArquitecturaTest {

    @Test
    void adaptadoresNoDebenDependerDirectamenteDeInfraestructura() {

        var importedClasses = new ClassFileImporter()
                .importPackages("com.empresa.pedidos");

        noClasses()
                .that()
                .resideInAPackage("..adaptadores..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..infraestructura..")
                .allowEmptyShould(true)
                .check(importedClasses);
    }
}