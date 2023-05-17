package com.testdbserver.desafiovotacao.utils.mocks;

import com.testdbserver.desafiovotacao.data.enums.VoteOptionEnum;
import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;

import java.util.Date;

public class VoteMocks {
    public static VoteDTO DEFAULT_VOTE_OBJ_OPTION_NO(){ return generateVoteObj(VoteOptionEnum.NO);}
    public static VoteDTO DEFAULT_VOTE_OBJ_OPTION_YES(){ return generateVoteObj(VoteOptionEnum.YES);}
    public static VoteDTO DEFAULT_VOTE_OBJ_OPTION_ABSTENTION(){ return generateVoteObj(VoteOptionEnum.ABSTENTION);}

    private static VoteDTO generateVoteObj(VoteOptionEnum voteOption) {
        return VoteDTO.builder().associateId(AssociateMocks.DEFAULT_ASSOCIATE_ID).sectionId(SectionMocks.SECTION_ID_1).votedAt(new Date()).voteOption(voteOption).build();
    }
}
