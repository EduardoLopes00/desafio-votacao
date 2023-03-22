package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class SearchSectionsFiltersDTO {
    @JsonProperty
    private Date maxDate;

    @JsonProperty(defaultValue = "false")
    private boolean allowFinishedSections;
}
