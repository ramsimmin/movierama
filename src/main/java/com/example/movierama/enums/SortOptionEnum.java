package com.example.movierama.enums;

public enum SortOptionEnum {

    DATE_DESC("created_at", "DESC"),
    DATE_ASC("created_at", "ASC"),
    LIKES_DESC("likes", "DESC"),
    LIKES_ASC("likes", "ASC"),
    HATES_DESC("hates", "DESC"),
    HATES_ASC("hates", "ASC");


    private String sortField;
    private String sortDirection;

    SortOptionEnum(String sortField, String sortDirection) {
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }

    public String getSortField() {
        return this.sortField;
    }

    public String getSortDirection() {
        return this.sortDirection;
    }


    }
