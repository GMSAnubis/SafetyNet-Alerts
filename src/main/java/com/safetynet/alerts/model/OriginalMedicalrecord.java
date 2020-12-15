package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
public class OriginalMedicalrecord {


  @JsonProperty("firstName")
  @NotBlank
  @NotNull
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank
  @NotNull
  private String lastName;

  @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonProperty("birthdate")
  @NotNull
  private LocalDate birthdate;

  @JsonProperty("medications")
  @NotNull
  private List<String> medications;

  @JsonProperty("allergies")
  @NotNull
  private List<String> allergies;

  public void setAllergies(List<String> allergies) {
    this.allergies = allergies;
  }

  public List<String> getAllergies() {
    return allergies;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setMedications(List<String> medications) {
    this.medications = medications;
  }

  public List<String> getMedications() {
    return medications;
  }

  @Override
  public String toString() {
    return
            "MedicalrecordsItem{"
                    + "allergies = '" + allergies + '\''
                    + ",firstName = '" + firstName + '\''
                    + ",lastName = '" + lastName + '\''
                    + ",birthdate = '" + birthdate + '\''
                    + ",medications = '" + medications + '\''
                    + "}";
  }
}