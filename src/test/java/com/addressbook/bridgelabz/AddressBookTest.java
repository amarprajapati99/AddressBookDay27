package com.addressbook.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookTest {

    @Test
    public void givenObject_WhenStoredInTheJson_ShouldReturn_TRUE() {
        Contacts contact = new Contacts("Amar",
                "Prajapati",
                "Somapur",
                "Azamgarh",
                "up",
                223223,
                8052636931L,
                "amarprajapati99@gmail.com");

        AddressBook addressBook = new AddressBook();
        boolean isAdded = addressBook.jsonWrite(contact);
        Assertions.assertTrue(isAdded);
    }

    @Test
    public void givenJsonFile_WhenRead_ShouldReturn_TRUE() {
        AddressBook addressBook = new AddressBook();
        boolean isRead = addressBook.jsonRead();
        Assertions.assertTrue(isRead);
    }
}
