package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;
import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/books")
@Api("Book")
public class BookEndPoint {

    @Inject
    private BookRepository bookRepository;

    @GET
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getooks() {
        List<Book> books = bookRepository.findAll();
        if (books.size() == 0)
            return Response.noContent().build();
        return Response.ok(books).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/count")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response countAllBooks() {
        Long nbOfBooks = bookRepository.countAll();
        return Response.ok(nbOfBooks).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.find(id);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.Create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).header("Access-Control-Allow-Origin", "*").build();
    }


}
