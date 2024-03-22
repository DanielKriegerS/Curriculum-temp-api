package com.danielks.curriculumapp.exceptions.object;

import com.danielks.curriculumapp.exceptions.InvalidRequestException;

public class InvalidObjectRequestException extends InvalidRequestException {
    public InvalidObjectRequestException(String invalidArgument) {
        super("The argument " + invalidArgument + "of the object is invalid!");
    }
}
