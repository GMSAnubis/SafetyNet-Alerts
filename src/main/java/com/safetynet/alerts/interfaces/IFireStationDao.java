package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;

public interface IFireStationDao {
  
  public FireStationList getFireStationListDto() throws JsonParseException, JsonMappingException, IOException;

  public FireStation postFireStationMapping();

  public FireStation updateFireStationNumber();

  public FireStation deleteFireStationMapping();

  
  
 

}
