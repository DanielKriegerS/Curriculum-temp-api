package com.danielks.curriculumapp.controllers;

import com.danielks.curriculumapp.entities.CurriculumObject;
import com.danielks.curriculumapp.entities.DTOs.CurriculumObjectDTO;
import com.danielks.curriculumapp.entities.mappers.CurriculumObjectMapper;
import com.danielks.curriculumapp.services.CurriculumObjectService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/objects")
public class CurriculumObjectController {

    private CurriculumObjectService service;
    private CurriculumObjectMapper mapper;

    public CurriculumObjectController(CurriculumObjectService service, CurriculumObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity<CurriculumObjectDTO> createCurriculumObject(@Valid @RequestBody CurriculumObjectDTO objectDTO) {
        CurriculumObjectDTO createdObject = service.createCurriculumObject(objectDTO);
        return new ResponseEntity<>(createdObject, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CurriculumObjectDTO>>> getAllCurriculumObjects() {
        List<CurriculumObjectDTO> objectsDTO = service.getAllCurriculumObjects();
        if (objectsDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<EntityModel<CurriculumObjectDTO>> entityModels = objectsDTO.stream()
                    .map(dto -> {
                        UUID id = dto.id();
                        Link selfLink = linkTo(CurriculumObjectController.class).slash(id).withSelfRel();
                        return EntityModel.of(dto, selfLink);
                    })
                    .collect(Collectors.toList());

            Link link = linkTo(CurriculumObjectController.class).withSelfRel();
            CollectionModel<EntityModel<CurriculumObjectDTO>> result = CollectionModel.of(entityModels, link);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CurriculumObjectDTO>> getCurriculumObjectById(@PathVariable UUID id) {
        Optional<CurriculumObjectDTO> objectDTO = service.getCurriculumObjectById(id);
        if(!objectDTO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            CurriculumObject object = mapper.INSTANCE.objectDTOToClass(objectDTO.get());
            Link selfLink = linkTo(CurriculumObjectController.class).slash(id).withSelfRel();
            object.add(selfLink);

            Link collectionLink = linkTo(methodOn(CurriculumObjectController.class).getAllCurriculumObjects()).withRel("Lista de objetos de currículo");

            CurriculumObjectDTO resultDTO = mapper.INSTANCE.classObjectToDTO(object);
            EntityModel<CurriculumObjectDTO> result = EntityModel.of(resultDTO, selfLink, collectionLink);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CurriculumObjectDTO> deleteCurriculumObject(@PathVariable UUID id) {
        service.deleteCurriculumObject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurriculumObjectDTO> updateCurriculumObject(@PathVariable UUID id,
                                                                      @RequestBody CurriculumObjectDTO objectDTO) {
        CurriculumObjectDTO updateObject =service.updateCurriculumObject(id, objectDTO);
        if (updateObject != null) {
            return new ResponseEntity<>(updateObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
