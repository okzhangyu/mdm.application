package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.vo.TaskRecord;
import javafx.concurrent.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBORepositoryTaskRecord extends CrudRepository<TaskRecord,String> {


    List<TaskRecord> findByObjectCodeAndIsSync(String objectCode, String isSync);
}
