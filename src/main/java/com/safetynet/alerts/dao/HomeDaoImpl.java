package com.safetynet.alerts.dao;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.model.HomeList;

@Component
public class HomeDaoImpl implements IHomeDao{

  @Override
  public HomeList getHomeListDao()
      throws JsonParseException, JsonMappingException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

}
