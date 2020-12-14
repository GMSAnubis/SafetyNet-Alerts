package com.safetynet.alerts.controller;

import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.utils.RequestLogger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CreateWorkingFileController {
  private static final Logger logger = LogManager.getLogger("App");

@Autowired
CreateWorkingFileService createWorkingFileService;

  @GetMapping("/createWorkingFile")
  public void createWorkingFile(final HttpServletResponse response,
                                final HttpServletRequest request) {
    logger.info(request.getMethod()  +" "+  request.getRequestURI());
    createWorkingFileService.createWorkingFile();
    response.setStatus(201);
    logger.info("Status : " + response.getStatus() + " WorkingFile created " );

  }


}
