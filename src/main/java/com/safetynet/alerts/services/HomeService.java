package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IHomeDto;
import com.safetynet.alerts.model.HomeList;

@Component
public class HomeService {
  
  @Autowired
  private IHomeDto homeDto;


  
  public HomeList getHome() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return homeDto.getHomeListDto();
  }

 

}
