package org.example.parcialmutante.exception;

import org.springframework.stereotype.Component;

@Component
public class DnaValidationExceptionHandler {

    public void validateDna(String[] dna) {
        // Validar que el Array no sea nulo
        if (dna == null) {
            throw new DnaException("El Array no puede ser nulo");
        }

        // Validar que el Array no esté vacío
        if (dna.length == 0) {
            throw new DnaException("El Array no puede estar vacio");
        }

        // Validar que el Array sea una matriz cuadrada
        int size = dna.length;
        for (String row : dna) {
            if (row.length() != size) {
                throw new DnaException("El Array debe ser una matriz cuadrada(NxN)");
            }
        }

        // Validar que el Array solo contenga A, T, G, C y no contenga numeros
        if (!isValidDna(dna)) {
            throw new DnaException("El Array solo puede contener caracteres: A, T, G, C");
        }

        // Validar que el Array no contenga todos los caracteres iguales
        if (isAllCharactersEqual(dna)) {
            throw new DnaException("El array no puede contener todos los caracteres iguales");
        }

        //Validar que el Array no sea NxN de nulls
        if (isAllNulls(dna)) {
            throw new DnaException("El array no puede ser de nulls");
        }
    }
    private boolean isValidDna(String[] dna) {
        return java.util.stream.IntStream.range(0, dna.length)
                .allMatch(i -> dna[i].matches("[ATGC]+") && !dna[i].matches("[0-9]"));
    }
    private boolean isAllCharactersEqual(String[] dna) {
        if (dna.length == 0) {
            return false;
        }
        char firstChar = dna[0].charAt(0);
        return java.util.stream.IntStream.range(0, dna.length)
                .allMatch(i -> dna[i].charAt(0) == firstChar);
    }
    private boolean isAllNulls(String[] dna) {
        return java.util.stream.IntStream.range(0, dna.length)
                .allMatch(i -> dna[i] == null);
    }

}
