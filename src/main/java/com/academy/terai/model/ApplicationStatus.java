package com.academy.terai.model;

public enum ApplicationStatus {
        FORM_RECEIVED("IT akademija gavo formą"),
        FORM_ACCEPTED("Priėmimas į akademiją patvirtintas"),
        FORM_REJECTED("Neigiamas atsakymas dėl priemimo į akademiją"),
        FORM_UNFINISHED("Registracijos forma nebaigta pildyti"),
        FORM_REVIEW("Registracijos forma yra peržiūrima");

    private String name;

    ApplicationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

