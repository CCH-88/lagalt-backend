package jact.lagaltproject.exceptions;

public class FreelancerAlreadyExistsException extends RuntimeException{

    public FreelancerAlreadyExistsException(String id) {super("Freelancer already exists with ID: " + id);}
}
