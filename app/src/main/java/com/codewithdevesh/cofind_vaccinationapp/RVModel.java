package com.codewithdevesh.cofind_vaccinationapp;

public class RVModel {
    String centerName;
    String centerAddress;
    String timingsFrom;
    String timingsTo;
    String fee;
    int ageLimit;
    String vaccineName;
    int capacity;

    public RVModel(String centerName, String centerAddress, String timingsFrom, String timingsTo, String fee, int ageLimit, String vaccineName, int capacity) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.timingsFrom = timingsFrom;
        this.timingsTo = timingsTo;
        this.fee = fee;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.capacity = capacity;
    }
    public RVModel(){

    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getTimingsFrom() {
        return timingsFrom;
    }

    public void setTimingsFrom(String timingsFrom) {
        this.timingsFrom = timingsFrom;
    }

    public String getTimingsTo() {
        return timingsTo;
    }

    public void setTimingsTo(String timingsTo) {
        this.timingsTo = timingsTo;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
