package ru.sberbank.javascool.client;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.*;

public class PersonTest {

    LocalDate birthDay = LocalDate.of(1985, 9, 11);

    Person person = new Person("Фёдор", "Михайлович", "Достоевский", birthDay);

    @Test
    public void fullName() {
        Assert.assertEquals(person.fullName(), "Фёдор Михайлович Достоевский");
        Person testPerson = new Person("  Иван  ", "  ", " Иванов ", birthDay);
        Assert.assertEquals(testPerson.fullName(), "Иван Иванов");
    }

    @Test
    public void age() {
        int age = Period.between(LocalDate.now(), birthDay).getYears();
        Assert.assertEquals(person.age(), age);
    }

    @Test
    public void getFirstName() {
        Assert.assertEquals(person.getFirstName(), "Фёдор");
    }

    @Test
    public void getMiddleName() {
        Assert.assertEquals(person.getMiddleName(), "Михайлович");
    }

    @Test
    public void getLastName() {
        Assert.assertEquals(person.getLastName(), "Достоевский");
    }

    @Test
    public void getBirthDay() {
        Assert.assertEquals(person.getBirthDay(), birthDay);
    }
}