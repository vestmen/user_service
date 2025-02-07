package user_service.controller.skill;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user_service.dto.skill.SkillCandidateDto;
import user_service.dto.skill.SkillDto;
import user_service.entity.Skill;
import user_service.mapper.skill.SkillMapper;
import user_service.service.skill.SkillService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/skill")
@RestController
public class SkillController {
    private final SkillService skillService;
    private final SkillMapper skillMapper;

    @PostMapping("/create")
    public SkillDto create(@RequestBody @Valid SkillDto skillDto) {
        Skill skill = skillService.createSkill(skillDto);
        return skillMapper.toDto(skill);
    }

    @GetMapping("/{userId}")
    public List<SkillDto> getUserSkills(@PathVariable long userId) {
        List<Skill> skills = skillService.getUserSkills(userId);
        return skills.stream()
                .map(skillMapper::toDto)
                .toList();
    }

    @GetMapping("/candidates/{userId}")
    public List<SkillCandidateDto> getOfferedSkills(@PathVariable long userId) {
        List<Skill> skills = skillService.getOfferedSkills(userId);
        return skillMapper.toSkillCandidateDtoList(skills);
    }

    @PutMapping("acquire/{skillId}/{userId}")
    public SkillDto acquireSkillFromOffers(@PathVariable long skillId, @PathVariable long userId) {
        Skill acquiredSkill = skillService.acquireSkillFromOffers(skillId, userId);
        return skillMapper.toDto(acquiredSkill);
    }
}
