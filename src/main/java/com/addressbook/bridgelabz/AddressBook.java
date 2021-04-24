package com.addressbook.bridgelabz;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
* zip,mobile number.*/

public class AddressBook {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Desktop\\CsvReader\\AddressBookIO";
        System.out.println("Starting File writing :" + filePath);
        writeCsv(filePath);
        System.out.println("starting read file");
        readCsv(filePath);
    }

    public static void writeCsv(String filePath) {
        List<Contacts> contacts = new ArrayList<Contacts>();
        Contacts contacts1 = new Contacts(filePath, filePath, filePath, filePath, filePath, filePath, filePath,
                filePath, filePath);
        contacts1.setFirstName("Prahlad");
        contacts1.setLastName("Prajapati");
        contacts1.setAddress("Azamgarh");
        contacts1.setCity("Azamgarh");
        contacts1.setState("up");
        contacts1.setZip("223223");
        contacts1.setMobileNumber("8052636931");
        contacts1.setEmailId("amarprajapati99@gmail.com");
        contacts1.add(contacts1);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.append("firstName,lastName,address,city,state,zip,mobileNumber,EmailId");
            for (Contacts ad : contacts) {
                fileWriter.append(String.valueOf(ad.getFirstName()));
                fileWriter.append(String.valueOf(ad.getLastName()));
                fileWriter.append(String.valueOf(ad.getAddress()));
                fileWriter.append(String.valueOf(ad.getCity()));
                fileWriter.append(String.valueOf(ad.getState()));
                fileWriter.append(String.valueOf(ad.getZip()));
                fileWriter.append(String.valueOf(ad.getMobileNumber()));
                fileWriter.append(String.valueOf(ad.getEmailId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void readCsv(String filePath) {
        BufferedReader reader = null;
        try {
            List<Contacts> empContacts = new ArrayList<Contacts>();
            String line = "";
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            while (( reader.readLine() != null)) {
                String[] field = line.split(",");
                if (!(field.length() <= 0)) {
                    Contacts contacts = new Contacts(field, field, field, field, field, field, field, field,
                            field);
                    contacts.setFirstName("Prahlad");
                    contacts.setLastName("Prajapati");
                    contacts.setAddress("Azamgarh");
                    contacts.setCity("Azamgarh");
                    contacts.setState("up");
                    contacts.setZip("223223");
                    contacts.setMobileNumber("8052636931");
                    contacts.setEmailId("amarprajapati99@gmail.com");
                    contacts.add(contacts);
                }
            }
            for (Contacts ad : empContacts) {
                System.out.printf(
                        "First Name = %s,Last Name = s, Address = %s, City = %s, State = %s,Zip = %s,Phone Number = %s,Email = %s",
                        ad.getFirstName(), ad.getLastName(), ad.getAddress(), ad.getCity(), ad.getState(), ad.getZip(),
                        ad.getMobileNumber(), ad.getEmailId());
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


