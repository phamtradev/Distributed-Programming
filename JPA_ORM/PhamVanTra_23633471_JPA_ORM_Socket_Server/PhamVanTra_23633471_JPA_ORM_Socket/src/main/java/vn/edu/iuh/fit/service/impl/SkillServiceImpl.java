package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.SkillDTO;
import vn.edu.iuh.fit.mapper.JacksonDataMapper;
import vn.edu.iuh.fit.model.Skill;
import vn.edu.iuh.fit.repository.impl.SkillRepositoryImpl;
import vn.edu.iuh.fit.service.SkillService;

import java.util.List;
import java.util.stream.Collectors;

public class SkillServiceImpl implements SkillService {

    private final SkillRepositoryImpl skillRepository = new SkillRepositoryImpl(Skill.class);
    private final JacksonDataMapper mapper = new JacksonDataMapper();

    @Override
    public List<SkillDTO> getAllSkills() {
        return skillRepository.loadAll().stream()
                .map(skill -> mapper.toObject(mapper.toMap(skill), SkillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO getSkillById(String id) {
        Skill skill = skillRepository.findById(id);
        return mapper.toObject(mapper.toMap(skill), SkillDTO.class);
    }

    @Override
    public SkillDTO saveSkill(SkillDTO skillDTO) {
        Skill skill = mapper.toObject(mapper.toMap(skillDTO), Skill.class);
        Skill saved = skillRepository.create(skill);
        return mapper.toObject(mapper.toMap(saved), SkillDTO.class);
    }

    @Override
    public void deleteSkill(String id) {
        skillRepository.delete(id);
    }
}
