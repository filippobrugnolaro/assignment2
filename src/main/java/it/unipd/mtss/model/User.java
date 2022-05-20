////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {
    private final String cf, name, surname, email;
    private final LocalDate birthdate;

    public User(String cf, String name, String surname,
         String email, LocalDate birth) {

        if(cf == null) {
            throw new IllegalArgumentException("CF must not be null");
        }

        if(name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        if(surname == null) {
            throw new IllegalArgumentException("Surname must not be null");
        }

        if(email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }

        if(birth == null || birth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birthday must be a valid date");
        }

        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.birthdate = birth;
        this.email = email;
    }

    public boolean isOver18() {
        return LocalDate.now().minusYears(18).isAfter(birthdate);
    }
}
