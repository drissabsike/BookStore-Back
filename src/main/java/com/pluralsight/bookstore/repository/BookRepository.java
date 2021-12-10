package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import static javax.transaction.Transactional.TxType.*;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager entityManager;


    @Inject
    private TextUtil textUtil;

    @Inject
    private NumberGenerator numberGenerator;


    //Create
    @Transactional(REQUIRED)
    public Book Create(@NotNull Book book){
        book.setTitle(textUtil.sanitize(book.getTitle()));
        book.setIsbn(numberGenerator.numberGenerator());
        entityManager.persist(book);
        return book;
    }

    //Find by Id
    public Book find(@NotNull Long id){
        return entityManager.find(Book.class, id);
    }

    // Delete by Id
    @Transactional(REQUIRED)
    public void delete(@NotNull Long id){
        entityManager.remove(entityManager.getReference(Book.class, id));
    }


    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

}
