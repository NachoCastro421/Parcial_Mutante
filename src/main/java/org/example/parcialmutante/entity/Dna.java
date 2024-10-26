package org.example.parcialmutante.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADN",columnDefinition = "text", unique = true)
    private String dna;

    private boolean isMutant;

    public void setDna(String[] dna) {
        this.dna = Arrays.toString(dna);
    }

}
