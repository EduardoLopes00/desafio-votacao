package com.testdbserver.desafiovotacao.utils.mocks;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.models.Pauta;

import java.util.UUID;

public class PautaMocks {
    public static UUID DEFAULT_PAUTA_ID = UUID.fromString("99e10f15-6279-4c3a-a922-14d75083d4e3");
    public static Pauta DEFAULT_PAUTA() {
        return Pauta.builder().id(DEFAULT_PAUTA_ID).subject("New spot in the city center").description("We are considering to build a new headquarters because[...]").sections(SectionMocks.SECTION_LIST()).build();
    }
}
