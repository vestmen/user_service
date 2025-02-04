package user_service.mapper.skill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import user_service.dto.skill.SkillCandidateDto;
import user_service.dto.skill.SkillDto;
import user_service.entity.Skill;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SkillMapperTest {
    private SkillMapper skillMapper;

    @BeforeEach
    void setUp() {
        skillMapper = Mappers.getMapper(SkillMapper.class);
    }

    @Test
    void toDto() {
        long id = 1L;
        String title = "title";
        Skill skill = Skill.builder().id(id).title(title).build();
        SkillDto skillDto = skillMapper.toDto(skill);
        assertEquals(id, skillDto.getId());
        assertEquals(title, skillDto.getTitle());
    }

    @Test
    void testToSkillCandidateDtoList() {
        Skill skill1 = build(1L, "Java");
        Skill skill2 = build(2L, "Python");
        Skill skill3 = build(1L, "Java");
        List<Skill> skills = Arrays.asList(skill1, skill2, skill3);

        List<SkillCandidateDto> result = skillMapper.toSkillCandidateDtoList(skills);
        Map<String, Long> allSkills = result.stream().collect(Collectors.toMap(
                val -> val.getSkillDto().getTitle(),
                SkillCandidateDto::getOfferAmount
        ));

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(2, allSkills.get("Java"));
        assertEquals(1, allSkills.get("Python"));
    }

    private Skill build(long id, String title) {
        return Skill.builder()
                .id(id)
                .title(title)
                .build();
    }

}