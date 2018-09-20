package com.avatech.edi.mdm.repository.config;

import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.config.DataTempleKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IRepositoryDataTemple extends CrudRepository<DataTemple,DataTempleKey> {

//    @Query("select code,lineId,u_tableName,u_fieldname from AVA_MDM_DTM1")
//    List<DataTemple> findByCode(String code);

    List<DataTemple> findByDataTempleKey_Code(String code);

}
