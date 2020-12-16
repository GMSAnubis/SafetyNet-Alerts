package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutResponse;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
@Repository
public class RetrieveOutPutDataRepositoryImpl implements RetrieveOutPutDataRepository {

  private static final Logger logger = LogManager.getLogger("App");


  @Override
  /**
   * @inheritDoc
   */
  public OutPutResponse getOutPutData(String constantFilePath) {
    String filePath = constantFilePath;
    ObjectMapper objectMapper = new ObjectMapper();
    OutPutResponse response = new OutPutResponse();

    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      logger.debug("Trying to create OutPutResponse");
      response = (objectMapper.readValue(new File(filePath),
              OutPutResponse.class));

    } catch (IOException e) {
      logger.error("IOException ", e);
    }
    logger.debug("Returng OutPutResponse created");
    return response;
  }


}





