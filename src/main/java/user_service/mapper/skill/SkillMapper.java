package user_service.mapper.skill;

import org.mapstruct.Mapper;
import user_service.dto.skill.SkillCandidateDto;
import user_service.dto.skill.SkillDto;
import user_service.entity.Skill;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SkillMapper {

    SkillDto toDto(Skill skill);

    default List<SkillCandidateDto> toSkillCandidateDtoList(List<Skill> skills) {
        Map<SkillDto, Long> skillMap = skills.stream()
                .collect(Collectors.groupingBy(
                        this::toDto,
                        Collectors.counting()
                ));
        return skillMap.entrySet()
                .stream()
                .map(entry -> new SkillCandidateDto((entry.getKey()), entry.getValue()))
                .toList();
    }

}
