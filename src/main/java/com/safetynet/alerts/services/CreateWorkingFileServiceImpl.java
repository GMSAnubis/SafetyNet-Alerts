package com.safetynet.alerts.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class CreateWorkingFileServiceImpl implements CreateWorkingFileService {
  final Logger logger = LogManager.getLogger("CreateWorkingFileServiceImpl");
  @Autowired
  RetrieveOriginalDataService retrieveOriginalDataService;
  @Autowired
  WorkingHomeService workingHomeService;
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  WorkingPersonsService workingPersonsService;
  @Autowired
  WorkingFirestationsService workingFireStationService;
  @Autowired
  WorkingMedicalRecordService workingMedicalRecordService;
  @Autowired
  OriginalPersonsService originalPersonsService;
  @Autowired
  OriginalMedicalRecordService originalMedicalRecordService;
  @Autowired
  OriginalFireStationService originalFireStationService;
  @Autowired
  CreateWorkingFileService createWorkingFileService;

  @Override
  /**
   * @inheritDoc
   */
  public void writeFile(WorkingResponse wr) {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      logger.debug("Writing Working File with: " + wr.toString());
      objectMapper.writeValue(new File(FilesPath.WORKING_INPUT_FILE), wr);

    } catch (IOException e) {
      logger.error("Problem creating working file with " + wr.toString() + " as parameter",
              e);
    }
    logger.debug("WorkingFile written");
  }

  @Override
  /**
   * @inheritDoc
   */
  @PostConstruct
  public void createWorkingFile() {
    logger.debug("Entering creating Working File: ");
    HashMap<String, WorkingHome> workingHomeHashMap =
            workingHomeService.getUnFinishedWorkingHomesHashMap();

    HashMap<String, WorkingMedicalRecord> workingMedicalRecordHashMap =
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap();

    HashMap<String, OriginalMedicalrecord> originalMedicalRecordHashMap =
            originalMedicalRecordService.getOriginalMedicalRecordHashMap();

    /*
    Linking Persons to home and medical records id
     */
    logger.debug("Linking Persons to home and medical records id ");
    HashMap<String, WorkingPerson> workingPersonsHashMap =
            workingPersonsService.getWorkingPersonsHashMap();
    HashMap<String, WorkingPerson> workingPersonsHashMapFinal = new HashMap<String,
            WorkingPerson>();
    logger.debug("linking persons with there medical record ");
    for (Map.Entry<String, WorkingPerson> me : workingPersonsHashMap.entrySet()) {
      String currentKeyvalue = me.getKey();
      String[] currentKeyValueProcessed = me.getKey().split(",");
      String keyNames = currentKeyValueProcessed[0] + "," + currentKeyValueProcessed[1];
      String keyAddress = currentKeyValueProcessed[2];
      UUID homeId = null;
      UUID medicalRecordId = null;
      LocalDate birthDate = null;
      WorkingHome currentHome = new WorkingHome();

      if (workingHomeHashMap.containsKey(keyAddress)) {
        homeId = workingHomeHashMap.get(keyAddress).getIdHome();
        currentHome = workingHomeHashMap.get(keyAddress);
      }

      if (workingMedicalRecordHashMap.containsKey(keyNames)) {
        medicalRecordId = workingMedicalRecordHashMap.get(keyNames).getIdMedicalRecord();
      }
      if (medicalRecordId == null) {
        medicalRecordId = new UUID(0L, 0L);
      }
      if (originalMedicalRecordHashMap.containsKey(keyNames)) {
        birthDate = originalMedicalRecordHashMap.get(keyNames).getBirthdate();
      }
      if (homeId != null && medicalRecordId != null) {
        WorkingPerson currentPerson = me.getValue();
        currentPerson.setHomeID(homeId);
        currentPerson.setIdMedicalRecord(medicalRecordId);
        currentPerson.setBirthdate(birthDate);

        workingPersonsHashMapFinal.put(currentKeyvalue, currentPerson);

        workingHomeHashMap.put(keyAddress, currentHome);

      }
    }

    /*
    Populating Home list of Firestations
     */
    logger.debug("Populating Home list of Firestations ");
    ArrayList<OriginalFirestation> originalFirestations =
            originalFireStationService.getOriginalFireStations();
    HashMap<Integer, WorkingFireStation> workingFireStationHashMap =
            workingFireStationService.createWorkingFiresStationHashMap();
    logger.debug("Populating firestation's home List");
    for (OriginalFirestation currentOriginalFirestation : originalFirestations) {
      WorkingHome addHome = new WorkingHome();
      WorkingFireStation addFireStation = new WorkingFireStation();
      String keyAddress = currentOriginalFirestation.getAddress();
      if (workingHomeHashMap.containsKey(keyAddress)) {
        addHome = workingHomeHashMap.get(keyAddress);
      }
      if (workingFireStationHashMap.containsKey(currentOriginalFirestation.getStation())) {
        addFireStation = workingFireStationHashMap.get(currentOriginalFirestation.getStation());
      }
      addFireStation.addWorkingHome(addHome.getIdHome());
      workingFireStationHashMap.put(currentOriginalFirestation.getStation(), addFireStation);
    }

    /*
    Adding list to be mapped
     */
    logger.debug("Create working response to send to writeFile()");
    WorkingResponse workingResponse = new WorkingResponse();

    workingResponse.setPersons(workingPersonsService.reestablishCase(workingPersonsHashMap.values()));
    workingResponse.setHomes(workingHomeService.reestablishCase(workingHomeHashMap.values()));
    workingResponse.setFirestations(workingFireStationService.reestablishCase(workingFireStationHashMap.values()));
    workingResponse.setMedicalrecords(workingMedicalRecordService.reestablishCase(workingMedicalRecordHashMap.values()));
    writeFile(workingResponse);
  }
}
