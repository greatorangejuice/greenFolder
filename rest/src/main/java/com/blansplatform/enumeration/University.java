package com.blansplatform.enumeration;

public enum University {
    BSUIR ("Белорусский Государственный Университет Информатики и Радиоэлектроники"),
    BNTU("Белорусский Национальный Технический Университет"),
    BSU("Белорусский Государственный Университет");


    private String fullName;
    University(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "University{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
