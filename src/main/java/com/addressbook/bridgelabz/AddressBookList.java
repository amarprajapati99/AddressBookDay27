package com.addressbook.bridgelabz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookList {
    private List<Contacts> addressBookList;
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public enum IOService {
        DB_IO, REST_IO
    }

    private List<Contacts> contacts;
    public AddressBook addressBook;

    public AddressBookList() {
        addressBook = AddressBook.getInstance();
    }

    public AddressBookList(List<Contacts> addressBook) {
        this();
        this.addressBookList = addressBook;
    }

    public List<Contacts> readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            return this.addressBookList = addressBook.getAddressBookDataUsingDB();
        return null;
    }

    public void updateContact(String address, String name) {
        addressBook.updateContactDetails(name, address);
    }

    public boolean checkAddressBookInSyncWithDB(String name) {
        List<Contacts> contacts = addressBook.getEmployeePayrollData(name);
        return contacts.get(0).equals(getEmployeePayrollData(name));
    }

    private Contacts getEmployeePayrollData(String name) {
        return this.addressBookList.stream().filter(employeePayrollDataItem ->
                employeePayrollDataItem.firstName.equals(name)).findFirst().orElse(null);
    }

    public List<Contacts> countByCity(String city) {
        return addressBook.getCount(city);
    }

    public List<Contacts> countByState(String state) {
        return addressBook.getCountByState(state);
    }

    public void addContactToAddressBook(String firstName, String lastName, String address, String city, String state, int zip, long mobileNumber, String email) {
        addressBookList.add(addressBook.addContact(firstName, lastName, address, city, state, zip, mobileNumber, email));
    }

    public void addContactIntoDB(List<Contacts> addressBookDataList) {
        addressBookDataList.forEach(data -> {
            this.addContactToAddressBook(data.firstName, data.lastName, data.address, data.city, data.state, data.zip, data.mobileNumber, data.emailId);
        });
    }

    public void addAddressBookDataWithThread(List<Contacts> addressBookDataList) {
        Map<Integer, Boolean> contactAdditionStatus = new HashMap<Integer, Boolean>();
        addressBookDataList.forEach(data -> {
            Runnable task = () -> {
                contactAdditionStatus.put(data.hashCode(), false);
                System.out.println("Contact is Being Added: " + Thread.currentThread().getName());
                this.addContactToAddressBook(data.firstName, data.lastName, data.address, data.city, data.state, data.zip, data.mobileNumber, data.emailId);
                contactAdditionStatus.put(data.hashCode(), true);
                System.out.println("Employee Added: " + Thread.currentThread().getName());
            };
            Thread thread = new Thread(task, data.firstName);
            thread.setPriority(10);
            thread.start();
        });
        while (contactAdditionStatus.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.addressBookList);
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            return this.addressBookList.size();
        return 0;
    }
}
