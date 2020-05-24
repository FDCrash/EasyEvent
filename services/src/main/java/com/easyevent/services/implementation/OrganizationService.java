package com.easyevent.services.implementation;

import com.easyevent.converters.OrganizationConverter;
import com.easyevent.dao.OrganizationRepository;
import com.easyevent.dto.base.OrganizationDto;
import com.easyevent.services.UserCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganizationService implements UserCrud<OrganizationDto> {

    private OrganizationConverter organizationConverter;
    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationConverter organizationConverter, OrganizationRepository organizationRepository) {
        this.organizationConverter = organizationConverter;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public String add(OrganizationDto organizationDto) {
        return String.valueOf(organizationRepository.save(organizationConverter.convert(organizationDto)).getId());
    }

    @Override
    public void delete(UUID id) {
        organizationRepository.delete(id);
    }

    @Override
    public List<OrganizationDto> getAll() {
        return organizationRepository.findAll().stream()
                .map(organizationEntity -> organizationConverter.convert(organizationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto get(UUID id) {
        return organizationConverter.convert(organizationRepository.findOne(id));
    }

    @Override
    public OrganizationDto getByLogin(String login) {
        return organizationConverter.convert(organizationRepository.findByLogin(login));
    }
}
