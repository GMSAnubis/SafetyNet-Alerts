package com.safetynet.alerts.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalFleService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalResponse;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFileServiceImpl implements OriginalFleService {
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalData;
  final Logger logger = LogManager.getLogger("App");

  @Override
  public OriginalResponse getOriginalResponse(String constantOriginalDataFile){
    return retrieveOriginalData.getOriginalData(constantOriginalDataFile);
  }
  @Override
  public void writeOriginalFile(OriginalResponse originalResponse){

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      logger.debug("Writing Working File with: " +originalResponse.toString());
      objectMapper.writeValue(new File(FilesPath.ORIGINAL_INPUT_FILE), originalResponse);

    } catch (IOException e) {
      logger.error("Problem creating working file with "+originalResponse.toString()+" as parameter",
              e);
    }
    logger.debug("WorkingFile written");
    //TODO code pour écrire le fichier data.json avec les données modifiées;
  }
}
