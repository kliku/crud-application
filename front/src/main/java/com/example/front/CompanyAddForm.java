package com.example.front;

public class CompanyAddForm {
    private String companyName;
    private String companyNip;

    public CompanyAddForm(String companyName, String companyNip) {
        this.companyName = companyName;
        this.companyNip = companyNip;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNip() {
        return companyNip;
    }

    public void setCompanyNip(String companyNip) {
        this.companyNip = companyNip;
    }
}
