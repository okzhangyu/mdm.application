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
}
