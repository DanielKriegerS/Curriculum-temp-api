package com.danielks.curriculumapp.entities;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "objects")
public class CurriculumObject extends RepresentationModel<CurriculumObject> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String objectOrigin;
    private String header;
    private String body;
    private String type;
    private Date beginDate;
    private Date endDate;
    private boolean ended;
    private Date creation;

    public CurriculumObject() {
    }

    public CurriculumObject(UUID id, String objectOrigin, String header, String body, String type, Date beginDate, Date endDate, boolean ended, Date creation) {
        this.id = id;
        this.objectOrigin = objectOrigin;
        this.header = header;
        this.body = body;
        this.type = type;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.ended = ended;
        this.creation = creation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getObjectOrigin() {
        return objectOrigin;
    }

    public void setObjectOrigin(String objectOrigin) {
        this.objectOrigin = objectOrigin;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurriculumObject that = (CurriculumObject) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
