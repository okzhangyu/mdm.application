package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.repository.IBORepositoryTaskRecord;
import com.avatech.edi.mdm.vo.TaskRecord;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskRecordService {

    private final Logger logger = LoggerFactory.getLogger(TaskRecordService.class);

    @Autowired
    private IBORepositoryTaskRecord taskRepository;

    public List<TaskRecord> fetchTaskRecord(String objectType){
        return taskRepository.findByObjectCodeAndIsSync(objectType,"N");
    }

    public List<TaskRecord> fetchTaskRecord(int docEntry){
        return taskRepository.findByDocEntry(docEntry);
    }

    public List<TaskRecord> fetchTaskRecordByCompany(String companyName){
        return taskRepository.findAllByCompanyName(companyName);
    }

    public List<TaskRecord> fetchErrorTaskRecord(){
        return taskRepository.findTop10ByIsSyncAndErrorTimeBefore("E",5);
    }

    public void updateTask(List<TaskRecord> taskRecords,boolean isSync,String message){
        String isSyncValue = null;
        if(isSync){
            isSyncValue = "Y" ;
        }else {
            isSyncValue = "E";
        }
        for (TaskRecord task:taskRecords) {
            task.setIsSync(isSyncValue);
            task.setSyncMessage(message);
        }
        taskRepository.saveAll(taskRecords);
    }

    public void updateTask(TaskRecord taskRecord){
        taskRepository.save(taskRecord);
    }
}
