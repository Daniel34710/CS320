package Testing;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import ContactApp.Contacts;


public class ContactsTest {
	
	@Test
	void testContactAndGetters() {
		Contacts contacts = new Contacts("123456", "High", "Tower", "2567789200", "1425 Baxter Dr.");
		assertTrue(contacts.getName().equals("High Tower"));
		assertTrue(contacts.getPhoneNumber().equals("2567789200"));
		assertTrue(contacts.getContactAddress().equals("1425 Baxter Dr."));
		assertTrue(contacts.getContactID().equals("123456"));
	}
	
	@Test
	void testSetFirstAndLastName() {
		Contacts contact = new Contacts("123457", "High", "Tower", "2567789200", "1425 Baxter Dr.");
		contact.setFirstName("High");
		contact.setLastName("Tower");
		assertTrue(contact.getName().equals("High Tower"));
	}
	
	@Test
	void testSetPhoneNumberAndAddress() {
		Contacts contact = new Contacts("123457", "High", "Tower", "2567789200", "1425 Baxter Dr.");
		contact.setPhoneNumber("2567789200");
		contact.setContactAddress("1425 Baxter Dr.");
		assertTrue(contact.getPhoneNumber().equals("2567789200"));
		assertTrue(contact.getContactAddress().equals("1425 Baxter Dr."));
	}
	
	
	@Test
	void testAllGetters() {
		Contacts contact = new Contacts("123457", "High", "Tower", "2567789200", "1425 Baxter Dr.");
		assertFalse(contact.getName().equals(""));
		assertFalse(contact.getContactID().equals(""));
		assertFalse(contact.getPhoneNumber().equals(""));
		assertFalse(contact.getContactAddress().equals(""));
	}

}
