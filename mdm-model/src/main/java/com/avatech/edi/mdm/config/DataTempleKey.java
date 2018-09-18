package com.avatech.edi.mdm.config;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DataTempleKey implements Serializable {

    @Column(name = "Code")
    private String code;

    @Column(name = "Lineid")
    private Integer lineId;

    public DataTempleKey() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((code == null) ? 0 : code.hashCode());
        result = PRIME * result + ((lineId == null) ? 0 : lineId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final DataTempleKey other = (DataTempleKey)obj;
        if(code == null){
            if(other.code != null){
                return false;
            }
        }else if(!code.equals(other.code)){
            return false;
        }
        if(lineId == null){
            if(other.lineId != null){
                return false;
            }
        }else if(!lineId.equals(other.lineId)){
            return false;
        }

        return true;
    }
}
