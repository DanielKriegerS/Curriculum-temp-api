package com.danielks.curriculumapp.services;

import com.danielks.curriculumapp.CurriculumAppApplication;
import com.danielks.curriculumapp.entities.CurriculumObject;
import com.danielks.curriculumapp.entities.DTOs.CurriculumObjectDTO;
import com.danielks.curriculumapp.entities.mappers.CurriculumObjectMapper;
import com.danielks.curriculumapp.exceptions.InvalidRequestException;
import com.danielks.curriculumapp.exceptions.object.InvalidObjectRequestException;
import com.danielks.curriculumapp.exceptions.object.ObjectNotFoundException;
import com.danielks.curriculumapp.repositories.CurriculumObjectRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CurriculumObjectService {

    private final CurriculumObjectRepository repository;
    private final CurriculumObjectMapper mapper;

    public CurriculumObjectService(CurriculumObjectRepository repository, CurriculumObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CurriculumObjectDTO createCurriculumObject(CurriculumObjectDTO objectDTO) {
        String typeExperience = "Experience";

        if (objectDTO.objectOrigin() == null){
            throw new InvalidObjectRequestException("Origin");
        }

        if (objectDTO.header() == null){
            throw new InvalidObjectRequestException("Header");
        }

        if (objectDTO.body() == null){
            throw new InvalidObjectRequestException("Body");
        }

        if (objectDTO.type() == null){
            throw new InvalidObjectRequestException("Type");
        } else if (objectDTO.type().equals(typeExperience) && objectDTO.beginDate() == null){
            throw new InvalidObjectRequestException("Begin date");
        }

        if (objectDTO.ended() && objectDTO.endDate() == null) {
            throw new InvalidObjectRequestException("End date - ended object");
        }

        if (!objectDTO.ended() && objectDTO.endDate() != null) {
            throw new InvalidObjectRequestException("End date - not ended object");
        }

        CurriculumObject createdObject = mapper.INSTANCE.objectDTOToClass(objectDTO);

        createdObject.setCreation(new Date());

        repository.save(createdObject);

        return mapper.INSTANCE.classObjectToDTO(createdObject);
    }

    public List<CurriculumObjectDTO> getAllCurriculumObjects() {
        List<CurriculumObject> objects = repository.findAll();
        return objects.stream()
                .map(mapper.INSTANCE::classObjectToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CurriculumObjectDTO> getCurriculumObjectById(UUID id) {
        Optional<CurriculumObject> optionalObject = repository.findById(id);

        if (optionalObject.isPresent()) {
            return optionalObject.map(mapper.INSTANCE::classObjectToDTO);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void deleteCurriculumObject(UUID id) {
        Optional<CurriculumObject> optionalObject = repository.findById(id);

        if (!optionalObject.isPresent()){
            throw new ObjectNotFoundException(id);
        }

        repository.deleteById(id);
    }

    public CurriculumObjectDTO updateCurriculumObject(UUID id, CurriculumObjectDTO objectDTO) {
        Optional<CurriculumObject> optionalObject = repository.findById(id);

        if (optionalObject.isPresent()){
            CurriculumObject existsObject = optionalObject.get();

            if (objectDTO.objectOrigin() != null) {
                existsObject.setObjectOrigin(objectDTO.objectOrigin());
            }

            if (objectDTO.header() != null) {
                existsObject.setHeader(objectDTO.header());
            }

            if (objectDTO.body() != null) {
                existsObject.setBody(objectDTO.body());
            }

            if (objectDTO.type() != null) {
                existsObject.setType(objectDTO.type());
            }

            if (objectDTO.beginDate() != null) {
                existsObject.setBeginDate(objectDTO.beginDate());
            }

            if (objectDTO.endDate() != null) {
                existsObject.setEndDate(objectDTO.endDate());
            }

            if (objectDTO.ended() != existsObject.isEnded()) {
                existsObject.setEnded(objectDTO.ended());
            }

            repository.save(existsObject);
            return mapper.INSTANCE.classObjectToDTO(existsObject);
        } else {
            throw new ObjectNotFoundException(id);
        }

    }
}
