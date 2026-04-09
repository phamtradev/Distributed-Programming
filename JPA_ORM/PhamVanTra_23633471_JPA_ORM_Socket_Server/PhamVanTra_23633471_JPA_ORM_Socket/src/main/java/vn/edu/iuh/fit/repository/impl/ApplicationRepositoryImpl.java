package vn.edu.iuh.fit.repository.impl;

import vn.edu.iuh.fit.model.Application;
import vn.edu.iuh.fit.model.ApplicationId;
import vn.edu.iuh.fit.repository.ApplicationRepository;

public class ApplicationRepositoryImpl extends GenericRepositoryImpl<Application, ApplicationId> implements ApplicationRepository {
    public ApplicationRepositoryImpl(Class<Application> entityClass) {
        super(entityClass);
    }
}
