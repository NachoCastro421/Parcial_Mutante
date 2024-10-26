package org.example.parcialmutante.controller;

import org.example.parcialmutante.exception.DnaException;
import org.example.parcialmutante.dto.DnaResponse;
import org.example.parcialmutante.dto.StatsResponse;
import org.example.parcialmutante.services.DnaService;
import org.example.parcialmutante.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mutant")
public class DnaController {

    @Autowired
    private DnaService dnaService;


    @PostMapping("")
    public ResponseEntity<?> isMutant(@RequestBody Map<String, String[]> dna) {
        try {
            //Obtener el array de ADN del mapa
            String[] dnaArray = dna.get("dna");
            //Determinar si el ADN es mutante
            boolean isMutant = dnaService.isMutant(dnaArray);
            //Guardar el ADN en el repositorio
            dnaService.saveDna(dnaArray, isMutant);
            //Crear la respuesta
            DnaResponse response = new DnaResponse(isMutant ? "Mutant detected" : "Forbidden", isMutant);
            //Devolver la respuesta HTTP
            return new ResponseEntity<>(response, isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN);
        } catch (DnaException e) {
            // Manejar la excepción y devolver una respuesta HTTP con el mensaje de error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
