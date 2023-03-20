package com.testdbserver.desafiovotacao.utils.mocks;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.infra.enums.SectionStatusEnum;
import com.testdbserver.desafiovotacao.utils.DateUtils;
import com.testdbserver.desafiovotacao.utils.UtilsFunctions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SectionMocks {
    public static UUID SECTION_ID_1 = UUID.fromString("6d506dba-2501-463f-ab11-e624182ac2a6");
    public static UUID SECTION_ID_2 = UUID.fromString("7fe9e44a-53b3-4afb-8bba-1a48e8bd8540");
    public static UUID SECTION_ID_3 = UUID.fromString("24149f38-1f57-4c5a-a106-848acd6ea29c");
    public static UUID SECTION_ID_4 = UUID.fromString("f9c3490b-f443-442c-b8b0-ac5bc675e295");

    public static Section SECTION_1() {
        Date now = DateUtils.now();

        return Section.builder()
                .id(SECTION_ID_1)
                .pauta(PautaMocks.DEFAULT_PAUTA())
                .status(SectionStatusEnum.WAITING_TO_START)
                .createdAt(now)
                .dtStart(DateUtils.addTime(DateUtils.week, now))
                .duration(DateUtils.hour).build();
    }

    public static Section SECTION_2() {
        Date now = DateUtils.now();

        return Section.builder()
                .id(SECTION_ID_2)
                .pauta(PautaMocks.DEFAULT_PAUTA())
                .status(SectionStatusEnum.WAITING_TO_START)
                .createdAt(now)
                .dtStart(DateUtils.addTime(2 * DateUtils.week, now))
                .duration(DateUtils.hour).build();
    }

    public static Section SECTION_3() {
        Date now = DateUtils.now();

        return Section.builder()
                .id(SECTION_ID_3)
                .pauta(PautaMocks.DEFAULT_PAUTA())
                .status(SectionStatusEnum.WAITING_TO_START)
                .createdAt(now)
                .dtStart(DateUtils.addTime(3 * DateUtils.week, now))
                .duration(DateUtils.hour).build();
    }

    public static Section SECTION_4() {
        Date now = DateUtils.now();

        return Section.builder()
                .id(SECTION_ID_4)
                .pauta(PautaMocks.DEFAULT_PAUTA())
                .status(SectionStatusEnum.WAITING_TO_START)
                .createdAt(now)
                .dtStart(DateUtils.addTime(4 * DateUtils.week, now))
                .duration(DateUtils.hour).build();
    }

    public static List<Section> SECTION_LIST() {
        List<Section> sectionList = new ArrayList<Section>();

        sectionList.add(SECTION_1());
        sectionList.add(SECTION_2());
        sectionList.add(SECTION_3());
        sectionList.add(SECTION_4());

        return sectionList;
    }

}
