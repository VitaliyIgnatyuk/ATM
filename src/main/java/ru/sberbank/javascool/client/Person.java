package ru.sberbank.javascool.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@AllArgsConstructor
public class Person {

  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate birthDay;

  public String fullName() {
      return String.format("%s %s %s", firstName, middleName, lastName).trim().replaceAll(" +", " ");
  }

  public int age() {
      if (birthDay == null)
          return 0;
      return Period.between(LocalDate.now(), birthDay).getYears();
  }

}
