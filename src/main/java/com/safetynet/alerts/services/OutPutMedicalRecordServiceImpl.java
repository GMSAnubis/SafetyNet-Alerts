package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutMedicalRecordService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.OutPutMedicalRecord;
import com.safetynet.alerts.model.WorkingMedicalRecord;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutMedicalRecordServiceImpl implements OutPutMedicalRecordService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  public OutPutMedicalRecord getMedicalRecordById(UUID medicalRecordId) {
    OutPutMedicalRecord medicalRecord = new OutPutMedicalRecord();
    ArrayList<OutPutMedicalRecord> outPutMedicalRecords =
            retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getMedicalrecords();

    for (OutPutMedicalRecord currentMedicalRecord : outPutMedicalRecords) {
      if (currentMedicalRecord.getIdMedicalRecord().equals(medicalRecordId)) {
        medicalRecord.setAllergies(currentMedicalRecord.getAllergies());
        medicalRecord.setMedications(currentMedicalRecord.getMedications());

        return medicalRecord;
      }
    }
    return null;
  }

  @Override
  public ArrayList<OutPutMedicalRecord> getAllMedicalRecords(){
    return retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getMedicalrecords();
  }
}
