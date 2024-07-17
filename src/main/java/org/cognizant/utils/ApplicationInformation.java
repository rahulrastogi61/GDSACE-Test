package org.cognizant.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationInformation {
    public String REF_ID;
    public String APP_STATUS;
    public String AGENCY_DETAILS;
    public String PROJECT_TITLE;

    public String getREF_ID() {
        return REF_ID;
    }

    public void setREF_ID(String REF_ID) {
        this.REF_ID = REF_ID;
    }

    public String getAPP_STATUS() {
        return APP_STATUS;
    }

    public void setAPP_STATUS(String APP_STATUS) {
        this.APP_STATUS = APP_STATUS;
    }

    public String getAGENCY_DETAILS() {
        return AGENCY_DETAILS;
    }

    public void setAGENCY_DETAILS(String AGENCY_DETAILS) {
        this.AGENCY_DETAILS = AGENCY_DETAILS;
    }

    public String getPROJECT_TITLE() {
        return PROJECT_TITLE;
    }

    public void setPROJECT_TITLE(String PROJECT_TITLE) {
        this.PROJECT_TITLE = PROJECT_TITLE;
    }
}
