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
        FileWriter filewriter1 = null;
        try {
            filewriter1 = new FileWriter(filePath);
            filewriter1.append("fName,lName,address,City,State,Zip,PhoneNumber,Email");
            for (Contacts ad : contacts) {
                filewriter1.append(String.valueOf(ad.getFirstName()));
                filewriter1.append(String.valueOf(ad.getLastName()));
                filewriter1.append(String.valueOf(ad.getAddress()));
                filewriter1.append(String.valueOf(ad.getCity()));
                filewriter1.append(String.valueOf(ad.getState()));
                filewriter1.append(String.valueOf(ad.getZip()));
                filewriter1.append(String.valueOf(ad.getMobileNumber()));
                filewriter1.append(String.valueOf(ad.getEmailId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                filewriter1.flush();
                filewriter1.close();
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


