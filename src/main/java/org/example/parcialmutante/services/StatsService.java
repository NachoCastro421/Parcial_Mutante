package org.example.parcialmutante.services;

import org.example.parcialmutante.dto.StatsResponse;
import org.example.parcialmutante.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private DnaRepository dnaRepository;

    public StatsResponse getDnaStats() {
        //Contar el numero de ADN mutantes y no mutantes
        long countMutantDna = dnaRepository.countByIsMutant(true);
        long countHumanDna = dnaRepository.countByIsMutant(false);
        //Calcular el ratio de ADN mutante respecto al humano
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
       //Crear y devolver una instancia con las estadisticas
        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}
