package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalFireStationService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFireStationServiceImpl implements OriginalFireStationService {
  final Logger logger = LogManager.getLogger("OriginalFireStationServiceImpl");
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalFirestation> deleteOriginalFireStation(OriginalFirestation originalFirestation,
                                                                  ArrayList<OriginalFirestation> originalFirestations) {
    logger.debug("Entering deleteOriginalFirestation ");
    ArrayList<OriginalFirestation> results = new ArrayList<>();
    for (OriginalFirestation currentFireStation : originalFirestations) {
      if (!originalFirestation.getAddress().equalsIgnoreCase(currentFireStation.getAddress())) {
        results.add(currentFireStation);
      }
    }
    logger.debug("deleteOriginalFirestation sucess");
    return results;
  }

  /**
   * @inheritDoc
   */
  @Override
  public boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                            ArrayList<OriginalFirestation> firestations
  ) {
    logger.debug("Entering isFireStationAlreadyInFile ");
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            getFireStationByNumberAndAddress(firestations, stationNumber, address);
    if (selectedFireStations.getAddress() != null || selectedFireStations.getStation() != null) {
      isAlreadyInFile = true;
    }
    logger.debug("isFireStationAlreadyInFile sucess ");
    return isAlreadyInFile;
  }

  /**
   * @inheritDoc
   */
  @Override
  public boolean isFireStationAlreadyInFile(Integer stationNumber,
                                            ArrayList<OriginalFirestation> firestations
  ) {
    logger.debug("Entering isFireStationAlreadyInFile ");
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            getFireStationByNumber(firestations, stationNumber);
    if (selectedFireStations.getStation() != null) {
      isAlreadyInFile = true;
    }
    logger.debug("isFireStationAlreadyInFile sucess return: ");
    return isAlreadyInFile;
  }

  /**
   * @inheritDoc
   */
  @Override
  public boolean isFireStationAlreadyInFile(String address,
                                            ArrayList<OriginalFirestation> firestations
  ) {
    logger.debug("Entering isFireStationAlreadyInFile ");
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            getFireStationByAddress(firestations, address);
    if (selectedFireStations.getAddress() != null) {
      isAlreadyInFile = true;
    }
    logger.debug("isFireStationAlreadyInFile sucess ");
    return isAlreadyInFile;
  }

  /**
   * @inheritDoc
   */
  @Override
  public boolean isAdressLinked(String address,
                                ArrayList<OriginalFirestation> firestations
  ) {
    logger.debug("Entering isAdressLinked ");
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            checkIfAdressExist(firestations, address);
    if (selectedFireStations.getAddress() != null) {
      isAlreadyInFile = true;
    }
    logger.debug("isFireStationAlreadyInFile sucess ");
    return isAlreadyInFile;
  }

  /**
   * @inheritDoc
   */
  @Override
  public OriginalFirestation checkIfAdressExist(ArrayList<OriginalFirestation> firestations,
                                                String address) {
    logger.debug("Entering checkIfAdressExist ");
    for (OriginalFirestation originalFirestation : firestations) {
      if (originalFirestation.getAddress().equalsIgnoreCase(address)) {
        logger.debug("Exit checkIfAdressExist " + originalFirestation.toString());
        return originalFirestation;
      }
    }
    logger.debug("Entering checkIfAdressExist not match found return new OriginalFirestation ");
    return new OriginalFirestation();
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalFirestation> getOriginalFireStations() {
    logger.debug("Entering getOriginalFireStations ");
    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashSet<OriginalFirestation> originalFireStations = new HashSet<>();

    for (OriginalFirestation originalFireStation : originalResponse.getFirestations()) {
      originalFireStations.add(originalFireStation);
    }
    ArrayList<OriginalFirestation> result =
            new ArrayList<OriginalFirestation>(originalFireStations);
    logger.debug("Exit success getOriginalFireStations ");
    return result;
  }

  /**
   * @inheritDoc
   */
  @Override
  public OriginalFirestation getFireStationByNumberAndAddress(ArrayList<OriginalFirestation> fireStations,

                                                              Integer stationNumber,
                                                              String address) {
    logger.debug("Entering getFireStationByNumberAndAddress ");
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getStation() == stationNumber.intValue() && originalFireStation.getAddress().equalsIgnoreCase(address)) {
        logger.debug("Exit getFireStationByNumberAndAddress ");
        return originalFireStation;
      }
    }
    logger.debug("Exit getFireStationByNumberAndAddress not match found return null");
    return new OriginalFirestation();
  }

  /**
   * @inheritDoc
   */
  @Override
  public OriginalFirestation getFireStationByAddress(ArrayList<OriginalFirestation> fireStations,
                                                     String address) {
    logger.debug("Entering getFireStationByAddress ");
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getAddress().equalsIgnoreCase(address)) {
        logger.debug("Exit getFireStationByAddress ");
        return originalFireStation;
      }
    }
    logger.debug("Exit getFireStationByAddress not match found return null ");
    return new OriginalFirestation();
  }

  /**
   * @inheritDoc
   */
  @Override
  public OriginalFirestation getFireStationByNumber(ArrayList<OriginalFirestation> fireStations,
                                                    Integer number) {
    logger.debug("entering getFireStationByNumber ");
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getStation() == number.intValue()) {
        logger.debug("Exit getFireStationByNumber ");
        return originalFireStation;
      }
    }
    logger.debug("Exit getFireStationByNumber  not match found new OriginalFirestation");
    return new OriginalFirestation();
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalFirestation> getFireStationsWithoutThisStation(ArrayList<OriginalFirestation> fireStations,
                                                                          Integer number) {
    logger.debug("entering getFireStationByNumber ");
    ArrayList<OriginalFirestation> originalFirestations = new ArrayList<>();
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getStation() != number.intValue()) {
        originalFirestations.add(originalFireStation);
      }
    }
    logger.debug("Exit getFireStationByNumber ");
    return originalFirestations;
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalFirestation> postNewFireStation(OriginalFirestation originalFirestation,
                                                           ArrayList<OriginalFirestation> originalFirestations) {
    logger.debug("entering postNewFireStation ");
    boolean isPresent = false;
    for (OriginalFirestation currentFirestation : originalFirestations) {
      if (originalFirestation.getAddress().equalsIgnoreCase(currentFirestation.getAddress()) &&
              originalFirestation.getStation().intValue() == currentFirestation.getStation().intValue()) {
        isPresent = true;
      }
    }
    if (isPresent) {
      logger.debug(" postNewFireStation Firestation already in return empty list");
      return originalFirestations;
    }
    ArrayList<OriginalFirestation> originalFirestationsResult = originalFirestations;
    originalFirestationsResult.add(originalFirestation);
    logger.debug("Success in postNewFireStation ");
    return originalFirestationsResult;
  }


}
