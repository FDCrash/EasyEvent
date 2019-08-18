package com.easyevent.services.implementation;

import com.easyevent.dto.OrganizationDto;
import com.easyevent.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService implements CrudService<OrganizationDto> {

    @Override
    public long add(OrganizationDto organizationDto) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<OrganizationDto> getAll() {
        return null;
    }

    @Override
    public OrganizationDto get(long id) {
        return null;
    }
}
