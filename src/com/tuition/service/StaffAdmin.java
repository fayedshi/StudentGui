package com.tuition.service;

import com.tuition.model.CasualStaff;
import com.tuition.model.Staff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffAdmin {

    public static List<Staff> loadStaff(String stfFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(stfFileName));
        String line;
        List<Staff> staffList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields[3].equalsIgnoreCase("Instructor")) {
                staffList.add(new CasualStaff(fields[1], fields[0], Double
                        .parseDouble(getMatched("(\\d+)", fields[2])), 0));
            }
        }
        br.close();
        return staffList;
    }

    // helper methods
    private static String getMatched(String regex, String dst) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(dst);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
