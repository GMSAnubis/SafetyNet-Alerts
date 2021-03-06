package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Contains original data format.
 */
@Component
public class OriginalPerson {


  @JsonProperty("firstName")
  @NotBlank
  @NotNull
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank
  @NotNull
  private String lastName;

  @JsonProperty("address")
  @NotBlank
  @NotNull
  private String address;

  @JsonProperty("city")
  @NotBlank
  @NotNull
  private String city;

  @JsonProperty("zip")
  @NotBlank
  @NotNull
  private String zip;

  @JsonProperty("phone")
  @NotBlank
  @NotNull
  private String phone;

  @JsonProperty("email")
  @NotBlank
  @NotNull
  private String email;

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return
            "Person{"
                    + ",firstName = '" + firstName + '\''
                    + ",lastName = '" + lastName + '\''
                    + ",address = '" + address + '\''
                    + ",zip = '" + zip + '\''
                    + ",city = '" + city + '\''
                    + ",phone = '" + phone + '\''
                    + ",email = '" + email + '\''
                    + "}";
  }
}