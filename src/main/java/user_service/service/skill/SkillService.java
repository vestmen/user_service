package user_service.service.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user_service.dto.skill.SkillDto;
import user_service.entity.Skill;
import user_service.exception.DataValidationException;
import user_service.repository.SkillRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillRepository skillRepository;

    @Transactional
    public Skill createSkill(SkillDto skillDto) {
        String title = skillDto.getTitle();
        if (skillRepository.existsByTitle(title)) {
            throw new DataValidationException("Title already exists");
        }
        Skill skill = Skill.builder()
                .title(title)
                .build();
        return skillRepository.save(skill);
    }

    public List<Skill> getUserSkills(long userId) {
        return skillRepository.findAllByUserId(userId);
    }

    public List<Skill> getOfferedSkills(long userId) {
        return skillRepository.findSkillsOfferedToUser(userId);
    }
}
