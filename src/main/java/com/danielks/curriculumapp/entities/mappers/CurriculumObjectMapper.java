package com.danielks.curriculumapp.entities.mappers;
import com.danielks.curriculumapp.entities.CurriculumObject;
import com.danielks.curriculumapp.entities.DTOs.CurriculumObjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CurriculumObjectMapper {
    CurriculumObjectMapper INSTANCE = Mappers.getMapper(CurriculumObjectMapper.class);

    @Mapping(target = "objectOrigin", source = "objectOrigin")
    @Mapping(target = "header", source = "header")
    @Mapping(target = "body", source = "body")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "beginDate", source = "beginDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "ended", source = "ended")
    @Mapping(target = "creation", source = "creation")
    CurriculumObjectDTO classObjectToDTO(CurriculumObject object);

    @Mapping(target = "objectOrigin", source = "objectOrigin")
    @Mapping(target = "header", source = "header")
    @Mapping(target = "body", source = "body")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "beginDate", source = "beginDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "ended", source = "ended")
    @Mapping(target = "creation", source = "creation")
    CurriculumObject objectDTOToClass(CurriculumObjectDTO objectDTO);
}
