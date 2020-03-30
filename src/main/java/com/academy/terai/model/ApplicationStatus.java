package com.academy.terai.model;

public enum ApplicationStatus {
        FORM_RECEIVED{
            @Override
            public String toString() {
                return "IT akademija gavo formą";
            }
        },
        FORM_ACCEPTED{
            @Override
            public String toString() {
                return "Priėmimas į akademiją patvirtintas";
            }
        },
        FORM_REJECTED{
            @Override
            public String toString() {
                return "Neigiamas atsakymas dėl priemimo į akademiją";
            }
        },
        FORM_UNFINISHED{
            @Override
            public String toString() {
                return "Registracijos forma nebaigta pildyti";
            }
        },
        FORM_REVIEW{
            @Override
            public String toString() {
                return "Registracijos forma yra peržiūrima";
            }
        };


}

