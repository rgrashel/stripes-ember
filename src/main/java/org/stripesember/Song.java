/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stripesember;

/**
 *
 * @author Rick_Grashel
 */
public class Song {

    private Long id;
    private String title;
    private Integer rating;

    public Song(Long id, String title, Integer rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public Long getId() {
        return this.id;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
