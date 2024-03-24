package HW4.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Customer implements Serializable {
    //region parameters:
    private String id;
    private static int customerCounter;
    private String surname;
    private Gender gender;
    private LocalDate birthDate;
    private long telephoneNumber;
    public enum Gender {MALE, FEMALE}
    //endregion parameters


    public Customer(String surname, Gender gender, LocalDate birthDate, long telephoneNumber) {
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.telephoneNumber = telephoneNumber;
        id = ++customerCounter + this.surname.substring(0, 3);
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return  "CustomerID: " + id +
                "\nsurname: " + surname +
                "\ngender: " + gender +
                "\nbirth date: " + birthDate +
                "\ntelephone number: " + telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return telephoneNumber == customer.telephoneNumber && Objects.equals(id, customer.id)
                && Objects.equals(surname, customer.surname) && gender == customer.gender
                && Objects.equals(birthDate, customer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, gender, birthDate, telephoneNumber);
    }
}
