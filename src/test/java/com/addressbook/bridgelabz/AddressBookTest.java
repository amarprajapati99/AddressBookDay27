package com.addressbook.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
