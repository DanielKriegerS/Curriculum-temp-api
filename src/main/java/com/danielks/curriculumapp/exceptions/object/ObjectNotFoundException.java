package com.danielks.curriculumapp.exceptions.object;

import com.danielks.curriculumapp.exceptions.EntityNotFoundException;

import java.util.UUID;

public class ObjectNotFoundException extends EntityNotFoundException {
    public ObjectNotFoundException(UUID id) {
        super("The object with ID " + id + " not founded.");
    }
}
