package org.example.parcialmutante.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponse {

    @JsonProperty("Count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("Count_human_dna")
    private long countHumanDna;

    private double ratio;

}
