package com.example.moviemax;

import java.util.ArrayList;

public class Show {
    private String name;
    private String releaseDate;
    private String status;
    private String trailerLink;
    private String imageLink;
    private String genre;
    private String type;

    public Show(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getType() {
        return type;
    }

    public String getImageLink() {
        return imageLink;
    }
}

