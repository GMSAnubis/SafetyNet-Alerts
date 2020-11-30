package com.safetynet.alerts.controller;

import java.util.ArrayList;

import com.safetynet.alerts.services.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FireStationController {

  /**
   * The Constant logger.
   */
  private static final Logger logger = LogManager.getLogger("App");
  @Autowired
  FireStationService fireStationService;

  @GetMapping("/firestation")
  public ArrayList<?> fireStation() {
    return fireStationService.getOriginalFireStations();
  }


}
