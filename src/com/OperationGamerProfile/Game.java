package com.OperationGamerProfile;

public class Game {
    private String company = "";
    private String platform = "";
    private String name = "";

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getPlatform() {
        return platform;
    }

    public void setName(String importedName) {
        name = importedName;
    }

    public void setCompany(String importedCompany) {
        company = importedCompany;
    }

    public void setPlatform(String importedPlatform) {
        platform = importedPlatform;
    }
}
