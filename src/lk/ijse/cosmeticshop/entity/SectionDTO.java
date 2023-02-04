package lk.ijse.cosmeticshop.entity;

/*
    @author BUDDINI
    @created 1/29/2023 - 9:16 AM   
*/


public class SectionDTO {
    String sectionCode;
    String sectionName;

    public SectionDTO() {
    }

    public SectionDTO(String sectionCode, String sectionName) {
        this.sectionCode = sectionCode;
        this.sectionName = sectionName;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionCode='" + sectionCode + '\'' +
                ", sectionName='" + sectionName + '\'' +
                '}';
    }
}
