package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.BusinessPartner;
import com.avatech.edi.mdm.bo.Material;
import org.springframework.data.repository.CrudRepository;

public interface IBORepositoryMaterial extends CrudRepository<Material,String> {
    Material findMaterialByUniqueKey(String uniqueKey);
}
