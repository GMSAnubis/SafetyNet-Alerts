package com.safetynet.alerts.controller;


import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.services.PersonService;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@WebMvcTest(value =PersonController.class)
public class PersonControllerTest {
  
  @MockBean
  private PersonService personService;
  
  @Autowired
  private PersonController personController;
  
 
  
 @Test
  public void getPersonListServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
   personController.getPersonInfo(); 
   verify(personService, times(1)).getpersons();
  }
}

