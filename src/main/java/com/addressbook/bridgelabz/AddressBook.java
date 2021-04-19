package com.addressbook.bridgelabz;

import java.util.Scanner;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
* zip,mobile number.*/

public class AddressBook {
    Contacts contacts;
    /* @Description- Add new contacts in address book  */
    private void addContacts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the first name");
        String firstName = scanner.nextLine();
        System.out.println("enter the last name");
        String lastName = scanner.nextLine();
        System.out.println("enter the address");
        String address = scanner.nextLine();
        System.out.println("enter the state");
        String state = scanner.nextLine();
        System.out.println("enter the city");
        String city = scanner.nextLine();
        System.out.println("enter the zip code");
        int zipCode = scanner.nextInt();
        System.out.println("enter the mobile number");
        long number = scanner.nextLong();
        System.out.println("enter email-id");
        String email = scanner.next();
        contacts = new Contacts(firstName, lastName, address, state, city, zipCode, number, email);
        System.out.println("contact successfully added");
    }
    @Override
    public String toString(){
        return contacts.toString();
    }
    public static void main(String[] args) {
        System.out.println("Welcome to address book system program");
        AddressBook addressBook = new AddressBook();
        addressBook.addContacts();
        System.out.println(addressBook.toString());
    }
}

