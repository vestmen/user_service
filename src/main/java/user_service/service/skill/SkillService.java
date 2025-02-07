package user_service.service.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user_service.dto.skill.SkillDto;
import user_service.entity.Skill;
import user_service.exception.DataValidationException;
import user_service.exception.OfferedSkillException;
import user_service.repository.SkillRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private static final int MIN_SKILL_OFFERS = 3;

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

    @Transactional
    public Skill acquireSkillFromOffers(long skillId, long userId) {
        Skill userSkill = skillRepository.findUserSkill(skillId, userId).orElse(null);
        if (userSkill != null) {
            throw new OfferedSkillException("User with id %d already have a skill with id %d", userId, skillId);
        }
        List<Skill> allOfferedSkills = getOfferedSkills(userId);
        List<Skill> offeredCurrentSkill = allOfferedSkills.stream()
                .filter(sk -> sk.getId() == skillId)
                .toList();
        if (offeredCurrentSkill.size() < MIN_SKILL_OFFERS) {
            throw new OfferedSkillException("Offered skills is less than the minimum skill offers");
        }
        skillRepository.assignSkillToUser(userId, skillId);
        return offeredCurrentSkill.get(0);
    }
}
