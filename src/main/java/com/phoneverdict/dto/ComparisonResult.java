package com.phoneverdict.dto;

import com.phoneverdict.model.Phone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparisonResult {

    private List<Phone> phones = new ArrayList<>();
    private Map<String, Integer> winners = new HashMap<>();
    private String verdictText;
    private int overallWinnerIndex = 0;

    public ComparisonResult() {
    }

    public ComparisonResult(List<Phone> phones, Map<String, Integer> winners, String verdictText, int overallWinnerIndex) {
        this.phones = phones;
        this.winners = winners;
        this.verdictText = verdictText;
        this.overallWinnerIndex = overallWinnerIndex;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Map<String, Integer> getWinners() {
        return winners;
    }

    public void setWinners(Map<String, Integer> winners) {
        this.winners = winners;
    }

    public String getVerdictText() {
        return verdictText;
    }

    public void setVerdictText(String verdictText) {
        this.verdictText = verdictText;
    }

    public int getOverallWinnerIndex() {
        return overallWinnerIndex;
    }

    public void setOverallWinnerIndex(int overallWinnerIndex) {
        this.overallWinnerIndex = overallWinnerIndex;
    }
}
