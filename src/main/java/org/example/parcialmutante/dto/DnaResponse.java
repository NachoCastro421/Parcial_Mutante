package org.example.parcialmutante.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DnaResponse {

    private String message;
    private boolean isMutant;

    public DnaResponse(String message, boolean isMutant) {
        this.message = message;
        this.isMutant = isMutant;
    }

    public boolean isMutant() {
        return isMutant;
    }

}
