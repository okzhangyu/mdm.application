package com.avatech.edi.mdm;

public class MDMMasterData implements IMDMMasterData {

    private String uniqueKey;
    @Override
    public String getUniqueKey() {
        return uniqueKey;
    }

    @Override
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
