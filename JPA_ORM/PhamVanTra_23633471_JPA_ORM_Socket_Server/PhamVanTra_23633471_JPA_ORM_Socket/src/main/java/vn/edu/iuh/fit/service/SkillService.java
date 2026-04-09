package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.SkillDTO;

import java.util.List;

public interface SkillService {

    List<SkillDTO> getAllSkills();

    SkillDTO getSkillById(String id);

    SkillDTO saveSkill(SkillDTO skillDTO);

    void deleteSkill(String id);
}
