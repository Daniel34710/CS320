package Testing;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import ContactApp.ContactService;
import ContactApp.Contacts;

public class ContactServiceTest {


	@Test
	void testAddContactMethod() {
		// create a contact
		ContactService contactService = new ContactService();
		String testID = contactService.generateUniqueId();
		Contacts contacts = new Contacts(testID, "Ron", "Davidson", "2056512055", "1425 Belt Drive");
		
		// add contact to the list
		contactService.addContact(contacts);
		
		// confirm contact is in the list, count is not zero
		assertTrue(!contactService.getContactList().isEmpty());
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getContactID()
				.equals(testID));
		assertTrue(contactService.getNumContacts() > 0);
	}
	
	@Test
	void testDeleteContactMethod() {
		ContactService contactService = new ContactService();
		// create new contact
		Contacts contact = new Contacts("358940", "Ron", "Davidson", "2056512055", "1425 Belt Drive");
		
		// try to remove with null id
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.deleteContacts(null);
		});
		
		// try to remove with id that's too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.deleteContacts("358940998745");
		});
		
		// try to remove from empty list
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.deleteContacts("3589409987");
		});
		
		// add the contact
		contactService.addContact(contact);
		
		// remove a contact that isn't there
		contactService.deleteContacts("358941");
		
		// contact list is not empty, count is not zero
		// contact not removed because contact doesn't exist
		assertTrue(!contactService.getContactList().isEmpty());
		assertTrue(contactService.getNumContacts() != 0);
		
		// remove correct contact
		contactService.deleteContacts("358940");
		
		// list is empty, count is zero, contact successfully removed
		assertTrue(contactService.getNumContacts() == 0);
		assertTrue(contactService.getContactList().isEmpty());
		
	}
	
	@Test
	void testUpdateContactMethodErrors() {
		ContactService contactService = new ContactService();
		// contact list is empty
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("35894", "Ronald", 1);
		});
		
		// create new contact, add to list
		Contacts contact = new Contacts("358940", "Ron", "Davidson", "2056512055", "1425 Belt Drive");
		contactService.addContact(contact);
		// check that contact was added
		assertTrue(!contactService.getContactList().isEmpty());
		
		// id is too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("35894012365", "Ronald", 1);
		});
		// id is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact(null, "Ronald", 1);
		});
		// update value is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("358940", null, 1);
		});
		// selection value is less than zero
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("358940", "Ronald", -1);
		});
		
		// print "contact not found" to console
		contactService.updateContact("358941", "Ronald", 1);
		
		// print "contact not updated" to console
		contactService.updateContact("358940", "Ronald", 5);
		
	}
	
	@Test
	void testUpdateContactMethod() {
		ContactService contactService = new ContactService();
		Contacts contact = new Contacts("358940", "Ron", "Dave", "2056512055", "1425 Belt Drive");
		contactService.addContact(contact);
		assertTrue(!contactService.getContactList().isEmpty());
		
		contactService.updateContact("358940", "Ronald", 1);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getName()
				.equals("Dave Ron"));

		contactService.updateContact("358940", "Ronald", 2);
		assertFalse(contactService
				.getContactList()
				.elementAt(0)
				.getName()
				.equals("Ronald"));

		contactService.updateContact("358940", "2056812502", 1);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getPhoneNumber()
				.equals("2056812502"));

		contactService.updateContact("358940", "6967 Beave Dr", 1);
		assertTrue(contactService
				.getContactList()
				.elementAt(0)
				.getContactAddress()
				.equals("6967 Beave Dr"));
		

		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("358940", "Ronald Inhram", 1);
		});
				
				
	}

}
