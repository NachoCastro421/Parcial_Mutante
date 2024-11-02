package org.example.parcialmutante.services;

import org.example.parcialmutante.entity.Dna;
import org.example.parcialmutante.exception.DnaException;
import org.example.parcialmutante.exception.DnaValidationExceptionHandler;
import org.example.parcialmutante.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class DnaService {

    @Autowired
    private DnaRepository dnaRepository;

    @Autowired
    private DnaValidationExceptionHandler dnaValidationExceptionHandler;

    //Determina si el ADN es mutante
    public boolean isMutant(String[] dna) {

        // Validar el ADN utilizando el manejador de excepciones
        dnaValidationExceptionHandler.validateDna(dna);
        // Contador de secuencias encontradas
        int sequenceCount = 0;
        int size = dna.length;

        // Verificar de manera horizontal, vertical y diagonal
        sequenceCount += checkAllHorizontal(dna,size,true);
        sequenceCount += checkAllVertical(dna,size,true);
        sequenceCount += checkAllDiagonals(dna,size,true);

        // Es mutante si encuentra más de una secuencia
        return sequenceCount > 1;
    }


    //Verifica todas las filas horizontales del ADN
    private int checkAllHorizontal(String[] dna, int size,boolean useHint) {
        return IntStream.range(0, size)
                .mapToObj(i -> dna[i])
                .mapToInt(sequence -> useHint ? findHintAndProcess(sequence, 1) : checkSequence(sequence))
                .sum();
    }
    //Verifica todas las filas verticales del ADn
    private int checkAllVertical(String[] dna, int size,boolean useHint) {
        StringBuilder column = new StringBuilder(size);
        return IntStream.range(0, size)
                .mapToObj(j -> {
                    column.setLength(0);
                    IntStream.range(0, dna.length)
                            .forEach(i -> column.append(dna[i].charAt(j)));
                    return column.toString();
                })
                .mapToInt(sequence -> useHint ? findHintAndProcess(sequence, 1) : checkSequence(sequence))
                .sum();
    }
    private int checkAllDiagonals(String[] dna, int size, boolean useHint) {
        int totalDiagonals = 0;

        // Procesar diagonales de izquierda a derecha
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - i; j++) {
                String diagonal = getDiagonal(dna, size, i, j);
                totalDiagonals += useHint ? findHintAndProcess(diagonal, 1) : checkSequence(diagonal);
            }
        }

        // Procesar diagonales de derecha a izquierda
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                String diagonal = getDiagonal(dna, size, i, j);
                totalDiagonals += useHint ? findHintAndProcess(diagonal, 1) : checkSequence(diagonal);
            }
        }

        return totalDiagonals;
    }

    private String getDiagonal(String[] dna, int size, int rowStart, int colStart) {
        StringBuilder diagonal = new StringBuilder(size);
        for (int i = 0; i < size && rowStart + i < size && colStart + i < size; i++) {
            diagonal.append(dna[rowStart + i].charAt(colStart + i));
        }
        return diagonal.toString();
    }
    //Encuentra pistas en la secunecia y las procesa
    private int findHintAndProcess(String sequence, int step) {
        int hintCount = 0;
        for (int i = 0; i < sequence.length() - 3; i += step) {
            if (sequence.charAt(i) == sequence.charAt(i + 1) &&
                    sequence.charAt(i + 1) == sequence.charAt(i + 2) &&
                    sequence.charAt(i + 2) == sequence.charAt(i + 3)) {
                hintCount++;
            }
        }
        return hintCount > 0 ? checkSequence(sequence) : 0;
    }
    //Verifica si el array contiene secuencias de 4 caracteres iguales
    private int checkSequence(String sequence) {
        int count = 0;
        char currentChar = sequence.charAt(0);
        int currentStreak = 1;

        // Contar secuencias de 4 caracteres iguales
        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == currentChar) {
                currentStreak++;
                if (currentStreak == 4) {
                    count++;
                    currentStreak = 0; // Reiniciar para contar otras secuencias
                }
            } else {
                currentChar = sequence.charAt(i);
                currentStreak = 1;
            }
        }

        return count;
    }
    //Guardar el ADN en el repositorio
    public Dna saveDna(String[] dna, boolean isMutant) {
        String dnaAsString = Arrays.toString(dna);

        // Verificar si ya existe un registro con este ADN
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaAsString);

        // Si ya existe, no guardar de nuevo
        if (existingDna.isPresent()) {
            return existingDna.get();  // Retorna el registro existente
        }

        Dna dnaEntity = new Dna();
        dnaEntity.setDna(dna);
        dnaEntity.setMutant(isMutant);
        return dnaRepository.save(dnaEntity);
    }
}
