package in.ac.iiitv.beyondbooks;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by anjul on 11/11/15.
 */
public class BookDetails {
    private Float public_ratings;
    private Float faculty_ratings;
    private Float student_ratings;
    private String about_book;
    private Boolean bookshelf;
    private Long isbn;
    private Integer id;
    private ArrayList<Integer> sellers;

    BookDetails(Float public_ratings, Float faculty_ratings, Float student_ratings, String about_book, Boolean bookshelf, Long isbn, Integer id, ArrayList<Integer> sellers){
        this.public_ratings = public_ratings;
        this.faculty_ratings = faculty_ratings;
        this.student_ratings = student_ratings;
        this.about_book = about_book;
        this.bookshelf = bookshelf;
        this.isbn = isbn;
        this.id = id;
        this.sellers = sellers;
    }

    public Float getPublic_ratings() {
        return public_ratings;
    }

    public void setPublic_ratings(Float public_ratings) {
        this.public_ratings = public_ratings;
    }

    public Float getStudent_ratings() {
        return student_ratings;
    }

    public void setStudent_ratings(Float student_ratings) {
        this.student_ratings = student_ratings;
    }

    public Float getFaculty_ratings() {
        return faculty_ratings;
    }

    public void setFaculty_ratings(Float faculty_ratings) {
        this.faculty_ratings = faculty_ratings;
    }

    public String getAbout_book() {
        return about_book;
    }

    public void setAbout_book(String about_book) {
        this.about_book = about_book;
    }

    public Boolean getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(Boolean bookshelf) {
        this.bookshelf = bookshelf;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Integer> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Integer> sellers) {
        this.sellers = sellers;
    }

}