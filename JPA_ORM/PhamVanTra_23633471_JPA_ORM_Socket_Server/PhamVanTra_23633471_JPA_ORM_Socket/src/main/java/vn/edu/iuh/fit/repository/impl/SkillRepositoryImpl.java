package vn.edu.iuh.fit.repository.impl;

import vn.edu.iuh.fit.model.Skill;
import vn.edu.iuh.fit.repository.SkillRepository;

public class SkillRepositoryImpl extends GenericRepositoryImpl<Skill, String> implements SkillRepository {
    public SkillRepositoryImpl(Class<Skill> entityClass) {
        super(entityClass);
    }
}
