package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingMedicalRecordService;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingMedicalRecord;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingMedicalRecordServiceImpl implements WorkingMedicalRecordService {

  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  /**
   * Key: String "firstname,lastname,birthdate".
   * Value: WorkingMedicalRecord with id generated in it.
   * @return HashMap<String, WorkingMedicalRecord>
   */


  public HashMap<String, WorkingMedicalRecord> getWorkingMedicalRecordsHashMap() {
    OriginalResponse originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, WorkingMedicalRecord> workingMedicalRecordHashMap = new HashMap<>();
    for (OriginalMedicalrecord originalMedicalrecords : originalResponse.getMedicalrecords()) {
      WorkingMedicalRecord workingMedicalRecord = new WorkingMedicalRecord();
      workingMedicalRecord.setAllergies(originalMedicalrecords.getAllergies());
      workingMedicalRecord.setMedications(originalMedicalrecords.getMedications());
      workingMedicalRecord.setIdMedicalRecord(UUID.randomUUID());
      workingMedicalRecordHashMap.put(originalMedicalrecords.getFirstName() + ","
              + originalMedicalrecords.getLastName(), workingMedicalRecord);
    }
    return workingMedicalRecordHashMap;
  }

}
