package com.myapp.myserver.service.impl;

import com.myapp.myserver.service.AppService;
import com.myapp.myserver.domain.App;
import com.myapp.myserver.repository.AppRepository;
import com.myapp.myserver.service.dto.AppDTO;
import com.myapp.myserver.service.mapper.AppMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing App.
 */
@Service
@Transactional
public class AppServiceImpl implements AppService{

    private final Logger log = LoggerFactory.getLogger(AppServiceImpl.class);

    private final AppRepository appRepository;

    private final AppMapper appMapper;

    public AppServiceImpl(AppRepository appRepository, AppMapper appMapper) {
        this.appRepository = appRepository;
        this.appMapper = appMapper;
    }

    /**
     * Save a app.
     *
     * @param appDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AppDTO save(AppDTO appDTO) {
        log.debug("Request to save App : {}", appDTO);
        App app = appMapper.toEntity(appDTO);
        app = appRepository.save(app);
        return appMapper.toDto(app);
    }

    /**
     * Get all the apps.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AppDTO> findAll() {
        log.debug("Request to get all Apps");
        return appRepository.findAll().stream()
            .map(appMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one app by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AppDTO findOne(Long id) {
        log.debug("Request to get App : {}", id);
        App app = appRepository.findOne(id);
        return appMapper.toDto(app);
    }

    /**
     * Delete the app by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete App : {}", id);
        appRepository.delete(id);
    }
}
