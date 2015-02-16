package rest;

import com.google.gson.Gson;
import com.google.gson.stream.*;
import com.google.gson.stream.JsonReader;
import com.sun.xml.internal.ws.util.Pool;
import entity.Book;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import service.BookService;

import javax.inject.Inject;
import javax.json.*;
import javax.json.stream.JsonParser;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.StringReader;
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
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book){
        Response.ResponseBuilder builder = null;
        try {


            validateBook(book);
            bookService.add(book);
            builder = Response.ok(book);

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
