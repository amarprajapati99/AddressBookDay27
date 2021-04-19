package com.addressbook.bridgelabz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
* zip,mobile number.*/

public class AddressBook {

    public int indexValue = 1;
    public HashMap<Integer,Contacts> contacts = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);
    static AddressBook addressbook = new AddressBook();
    /* @Description- Add new contacts in address book  */
    private void addContacts() {
        System.out.println("Enter Number of person you want to add");
        int numOfPerson = sc.nextInt();
        for (int add = 1; add <= numOfPerson; add++){
        System.out.println("enter the first name");
        String firstName = sc.nextLine();
        System.out.println("enter the last name");
        String lastName = sc.nextLine();
        System.out.println("enter the address");
        String address = sc.nextLine();
        System.out.println("enter the state");
        String state = sc.nextLine();
        System.out.println("enter the city");
        String city = sc.nextLine();
        System.out.println("enter the zip code");
        int zipCode = sc.nextInt();
        System.out.println("enter the mobile number");
        long number = sc.nextLong();
        System.out.println("enter email-id");
        String email = sc.next();
            if (addressbook.check(firstName)) {
                add--;
                continue;
            }
            Contacts contact = new Contacts(firstName, lastName, address, city, state, zipCode, number, email);
            contacts.put(indexValue, contact);
            indexValue++;
        }
        System.out.println("\nContacts added Successfully");
    }
    /* to check duplicate entry if find duplicate are not allowed */

    private boolean check(String firstName) {
        if (contacts.isEmpty())
            return false;
        else {
            System.out.println("\nAdd contact again with different first name.");
            Iterator<Integer> itr = contacts.keySet().iterator();
            while (((Iterator<?>) itr).hasNext()) {
                int key = itr.next();
                if (contacts.get(key).firstName.equals(check(firstName))) {
                    System.out.println("\nAdd contact again with different first name.");
                    return true;
                }
            }
        }
        return false;
    }
    /* Description - edit contacts address book */
    
    public void editContact(){
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            System.out.println("Enter the first name to edit contact.");
            String name = sc.next();
            Iterator<Integer> itr = contacts.keySet().iterator();
            while(itr.hasNext()) {
                int key = itr.next();
                if (contacts.get(key).firstName.equals(name)) {
                    System.out.println("\nEnter First Name to Edit");
                    String first = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Last Name to Edit");
                    String last = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Address to Edit");
                    String address = sc.next();
                    sc.nextLine();
                    System.out.println("Enter City to Edit");
                    String city = sc.nextLine();
                    System.out.println("Enter State to Edit");
                    String state = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Zip Code to Edit");
                    int zip = sc.nextInt();
                    System.out.println("Enter Phone Number to Edit");
                    long phone = sc.nextLong();
                    System.out.println("Enter E-mail to Edit");
                    String email = sc.next();
                    Contacts contact = new Contacts(first, last, address, city, state, zip, phone, email);
                    contact.put(key, contact);
                    System.out.println("Contact edited with given first name : "+name);
                }
            }
        }
    }
    
    /* Description - delete contacts in address book  using their name */
    public void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            System.out.println("Enter the first name to delete contact.");
            String name = sc.next();
            Iterator<Integer> itr = contacts.keySet().iterator();
            while(itr.hasNext()) {
                int key = itr.next();
                if (contacts.get(key).firstName.equals(name)) {
                    contacts.remove(key);
                    System.out.println("Contact deleted with first name : "+name);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        AddressBook addressbook = new AddressBook();
        addressbook.addContacts();
        int choice = 1;
        do {
            System.out.println("Enter your choice\n1. Add Contact\n2. Edit Contact\n3. Delete Contact\n3. Exit");
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    addressbook.addContacts();
                    break;
                case 2:
                    addressbook.editContact();
                    break;
                case 3 :
                    addressbook.deleteContact();
                    break;
                default:
                    System.out.println("You press exit.\nThank You!");
                    choice = 0;
                    break;
            }
        }
        while (choice != 0);
    }
}


