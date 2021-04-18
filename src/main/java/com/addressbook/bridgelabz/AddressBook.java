package com.addressbook.bridgelabz;

/* @Description - To create a contacts in address book with first name, last name, address, city, state,
* zip,mobile number.*/
public class AddressBook {
    public static void main(String[] args) {
        System.out.println("Welcome to address book system program");
        Contacts contact = new Contacts("Rudra", "Prajapati", "Old madivala",
                "Benglore", "Karnataka", 560068,
                91805644558L, "rudra@gmail.com");
        System.out.println(contact.toString());
    }
}

