package user_service.service.skill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user_service.dto.skill.SkillDto;
import user_service.entity.Skill;
import user_service.exception.DataValidationException;
import user_service.exception.OfferedSkillException;
import user_service.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    private final long skillId = 1L;
    private final String title = "Java";
    private final SkillDto skillDto = SkillDto.builder()
            .title(title)
            .build();
    private final Skill skill = Skill.builder()
            .id(skillId)
            .title(title)
            .build();
    private final long userId = 1L;


    @Test
    void createSkill_ShouldSaveSkill_WhenTitleIsUnique() {
        Skill skill = Skill.builder().id(1L).title(title).build();

        when(skillRepository.existsByTitle(title)).thenReturn(false);
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        Skill result = skillService.createSkill(skillDto);

        assertNotNull(result);
        assertEquals(title, result.getTitle());
        verify(skillRepository).save(any(Skill.class));
    }

    @Test
    void createSkill_ShouldThrowException_WhenTitleAlreadyExists() {
        when(skillRepository.existsByTitle(title)).thenReturn(true);

        assertThrows(DataValidationException.class, () -> skillService.createSkill(skillDto));
    }

    @Test
    void getUserSkills_ShouldReturnUserSkills() {
        List<Skill> skills = List.of(
                skill,
                Skill.builder().id(2L).title("Spring").build()
        );

        when(skillRepository.findAllByUserId(userId)).thenReturn(skills);

        List<Skill> result = skillService.getUserSkills(userId);

        assertEquals(2, result.size());
        verify(skillRepository).findAllByUserId(userId);
    }

    @Test
    void getOfferedSkills_ShouldReturnOfferedSkills() {
        List<Skill> offeredSkills = List.of(
                skill
        );

        when(skillRepository.findSkillsOfferedToUser(userId)).thenReturn(offeredSkills);

        List<Skill> result = skillService.getOfferedSkills(userId);

        assertEquals(1, result.size());
        assertEquals(title, result.get(0).getTitle());
        verify(skillRepository).findSkillsOfferedToUser(userId);
    }

    @Test
    void acquireSkillFromOffers_ShouldAssignSkill_WhenEnoughOffersExist() {
        List<Skill> offeredSkills = List.of(
                skill,
                Skill.builder().id(11L).title("Go").build(),
                skill,
                Skill.builder().id(12L).title("Rust").build(),
                skill
        );

        when(skillRepository.findUserSkill(skillId, userId)).thenReturn(Optional.empty());
        when(skillRepository.findSkillsOfferedToUser(userId)).thenReturn(offeredSkills);
        doNothing().when(skillRepository).assignSkillToUser(userId, skillId);

        Skill result = skillService.acquireSkillFromOffers(skillId, userId);

        assertNotNull(result);
        assertEquals(skillId, result.getId());
        verify(skillRepository).assignSkillToUser(userId, skillId);
    }

    @Test
    void acquireSkillFromOffers_ShouldThrowException_WhenUserAlreadyHasSkill() {
        Skill existingSkill = Skill.builder().id(skillId).title("Kotlin").build();

        when(skillRepository.findUserSkill(skillId, userId)).thenReturn(Optional.of(existingSkill));

        assertThrows(OfferedSkillException.class, () -> skillService.acquireSkillFromOffers(skillId, userId));
    }

    @Test
    void acquireSkillFromOffers_ShouldThrowException_WhenNotEnoughOffers() {
        List<Skill> offeredSkills = List.of(
                Skill.builder().id(skillId).title("Kotlin").build()
        );

        when(skillRepository.findUserSkill(skillId, userId)).thenReturn(Optional.empty());
        when(skillRepository.findSkillsOfferedToUser(userId)).thenReturn(offeredSkills);

        assertThrows(OfferedSkillException.class, () -> skillService.acquireSkillFromOffers(skillId, userId));
    }
}
