package com.safetynet.alerts.interfaces;


import com.safetynet.alerts.model.WorkingMedicalRecord;
import java.util.HashMap;

/**
 * The Interface IMedicalRecordDao.
 */
public interface WorkingMedicalRecordService {
  /**
   * Key: String "firstname,lastname,birthdate".
   * Value: WorkingMedicalRecord with id generated in it.
   * @return HashMap<String, WorkingMedicalRecord>
   */
  HashMap<String, WorkingMedicalRecord> getWorkingMedicalRecordsHashMap();

}
