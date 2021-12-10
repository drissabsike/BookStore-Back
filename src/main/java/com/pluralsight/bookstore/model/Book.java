package com.pluralsight.bookstore.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    @NotNull
    @Size(min = 1, max = 200)
    private String title;

    @Column(length = 10000)
    @Size(min = 1, max = 10000)
    private String description;

    @Column(name = "unit_cost")
    @Min(1)
    private Float unitCost;

    @Column(length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String isbn;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    @Past
    private Date publicationDate;

    @Column(name = "nb_of_pages")
    private Integer nbOfPages;

    @Column(name = "image_url")
    private String imageURL;

    @Enumerated
    private Language language;

    public Book(){}

    public Book(String title, String description, Float unitCost, String isbn, Date publicationDate, Integer nbOfPages, String imageURL, Language language) {
        this.title = title;
        this.description = description;
        this.unitCost = unitCost;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.nbOfPages = nbOfPages;
        this.imageURL = imageURL;
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", nbOfPages=" + nbOfPages +
                ", imageURL='" + imageURL + '\'' +
                ", language=" + language +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


}
