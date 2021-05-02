package com.addressbook.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressBookTest {
    List<Contacts> addressBookContactList;

    @Test
    public void givenThreeContactsInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookList addressBookList = new AddressBookList();
        addressBookContactList = addressBookList.readAddressBookData(AddressBookList.IOService.DB_IO);
        Assertions.assertEquals(3, addressBookContactList.size());
    }
}
