package com.addressbook.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class AddressBookTest {
    AddressBookList addressBookList = new AddressBookList();
    List<Contacts> addressBookContactList;

    @Test
    public void givenThreeContactsInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookList addressBookList = new AddressBookList();
        addressBookContactList = addressBookList.readAddressBookData(AddressBookList.IOService.DB_IO);
        Assertions.assertEquals(3, addressBookContactList.size());
    }
    @Test
    public void givenContactDataInDB_whenUpdated_ShouldSyncWithDB() {
        addressBookList = new AddressBookList();
        addressBookContactList = addressBookList.readAddressBookData(AddressBookList.IOService.DB_IO);
        addressBookList.updateContact("Somapur", "Prahlad");
        boolean result = addressBookList.checkAddressBookInSyncWithDB("Prahlad");
        Assertions.assertTrue(result);
    }
    @Test
    public void givenContactDataInDB_whenCountByCity_ShouldMatchWithExpectedValue() {
        addressBookList = new AddressBookList();
        List<Contacts>  addressBookDataList = addressBookList.countByCity("Azamgarh");
        Assertions.assertEquals(3, addressBookDataList.size());
    }

    @Test
    public void givenContactDataInDB_whenCountByState_ShouldMatchWithExpectedValue() {
        addressBookList = new AddressBookList();
        List<Contacts>  addressBookDataList = addressBookList.countByState("up");
        Assertions.assertEquals(2, addressBookDataList.size());
    }
    @Test
    public void givenNewContactData_WhenAdded_ShouldSyncWithDB() {
        addressBookList = new AddressBookList();
        addressBookContactList = addressBookList.readAddressBookData(AddressBookList.IOService.DB_IO);
        addressBookList.addContactToAddressBook("Amar", "Prajapati", "Somapur", "Azamgarh", "up",
                2223223, 9450807515L, "amar@gmail.com");
        boolean result = addressBookList.checkAddressBookInSyncWithDB("Amar");
        Assertions.assertTrue(result);
    }
    @Test
    public void given6Employees_WhenAdded_Should_ShouldMatchEmpEntries() {
        Contacts[] arrayOfContacts = {
                new Contacts(0, "Chandan", "Singh", "Sai Nagar","Lucknow", "up", 226001,
                        7539641250L, "chandan@gmail.com"),
                new  Contacts(0, "Ganesh", "singh", "Keshav Nagar","Pune", "MH", 969654,
                        9724315568L, "ganesh.singh@gmail.com"),
                new Contacts(0, "Mukesh", "Prajapati", "Mahadeva","Akabrpur", "up", 549678,
                        9254613757L, "mk.1sep@gmail.com")
        };
        AddressBookList addressBookList = new AddressBookList();
        addressBookList.readAddressBookData(AddressBookList.IOService.DB_IO);
        Instant start = Instant.now();
        addressBookList.addContactIntoDB(Arrays.asList(arrayOfContacts));
        Instant end = Instant.now();
        System.out.println("Duration without Thread: " + Duration.between(start, end));
        Instant threadStart = Instant.now();
        addressBookList.addAddressBookDataWithThread(Arrays.asList(arrayOfContacts));
        Instant threadEnd = Instant.now();
        System.out.println("Duration with thread: " + Duration.between(threadStart, threadEnd));
        Assertions.assertEquals(13, addressBookList.countEntries(AddressBookList.IOService.DB_IO));
    }
}
