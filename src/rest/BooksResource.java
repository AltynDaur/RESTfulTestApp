package rest;

import entity.Book;
import service.BookService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Path("books")
public class BooksResource {

    @Inject
    private BookService bookService;

    @Inject
    private Validator validator;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id:[0-9][0-9]*}")
    public Book getBook(@PathParam("id")int id){
        Book book = bookService.findByID(id);
        if(book == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return book;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book){
        Response.ResponseBuilder builder = null;
        try {
            validateBook(book);
            bookService.add(book);
        } catch (ConstraintViolationException e){
            builder = createViolationResponse(e.getConstraintViolations());
        } catch (Exception e){
            Map<String,String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> constraintViolations) {
        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : constraintViolations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    private void validateBook(Book book) throws ConstraintViolationException{
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolations));
        }

    }

}
