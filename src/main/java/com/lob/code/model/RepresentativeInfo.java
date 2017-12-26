
package com.lob.code.model;

import java.util.List;

public class RepresentativeInfo {

    private String kind;
    private NormalizedInput normalizedInput;
    private Divisions divisions;
    private List<Office> offices = null;
    private List<Official> officials = null;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public NormalizedInput getNormalizedInput() {
        return normalizedInput;
    }

    public void setNormalizedInput(NormalizedInput normalizedInput) {
        this.normalizedInput = normalizedInput;
    }

    public Divisions getDivisions() {
        return divisions;
    }

    public void setDivisions(Divisions divisions) {
        this.divisions = divisions;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public List<Official> getOfficials() {
        return officials;
    }

    public void setOfficials(List<Official> officials) {
        this.officials = officials;
    }

}
