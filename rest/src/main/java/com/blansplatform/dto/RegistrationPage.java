package com.blansplatform.dto;

import com.blansplatform.enumeration.University;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationPage {

    private Map<University, String> allUniversityAsMap;

    public RegistrationPage() {
        this.allUniversityAsMap = getMapFromEnum();
    }

    public Map<University, String> getAllUniversityAsMap() {
        return allUniversityAsMap;
    }

    public void setAllUniversityAsMap(Map<University, String> allUniversityAsMap) {
        this.allUniversityAsMap = allUniversityAsMap;
    }

    private HashMap<University, String> getMapFromEnum() {
        HashMap<University, String> map = new HashMap<University, String>();
        for (University u : University.values()) {
            map.put(u, u.getFullName());
        }
        return map;
    }

}


