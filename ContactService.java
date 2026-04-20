package ContactApp;

import java.util.Vector;
import java.util.Random;

public class ContactService {
	
	// Create a vector to store contacts.
	public Vector<Contacts> myContacts = new Vector<Contacts>();
	
	
	public int numContacts = 0;
	
	
	public int getNumContacts() {
		return numContacts;
	}
	

	public Vector<Contacts> getContactList(){
		return myContacts;
	}
	

	public void addContact(String contactID, String fName, String lName, String phoneNumber, String contactAddress) {

		Contacts newContacts = new Contacts(
				contactID, 
				fName, 
				lName, 
				phoneNumber, 
				contactAddress);
		
		myContacts.add(newContacts);
		

		numContacts++;
	}
	
	public void addContact(Contacts contacts) {

		myContacts.add(contacts);
		
		numContacts++;
	}
	
	public void deleteContacts(String contactsID) {
		
		if (contactsID == null || contactsID.length() > 10) {
			throw new IllegalArgumentException("Invalid contact ID");
		}
		
		if (myContacts.isEmpty()) {
			throw new IllegalArgumentException("No contacts saved.");
		}
		
		int index = -1;
		for (Contacts c: myContacts) {
			if (c.getContactID() == contactsID) {
				index = myContacts.indexOf(c);
			}
		}
		
		if (index == -1) {
			System.out.println("Not found.");
			return;
		}
		
		else {
			myContacts.remove(index);
			numContacts--;
			System.out.println("Removed.");
		}
	}
	
	public void deleteContact(Contacts contact) {
		myContacts.remove(contact);
		numContacts --;
	}
	
	// Update contact
	public void updateContact(String ID, String update, int selection) {
		if (ID == null || ID.length() > 10 || update == null || selection < 0) {
			throw new IllegalArgumentException("Invalid Contact ID");
		}
		
		if (myContacts.isEmpty()) {
			throw new IllegalArgumentException("No contacts to update.");
		}
		
		int index = -1;
		
		for (Contacts c: myContacts) {
			if (c.getContactID() == ID) {
				index = myContacts.indexOf(c);
			}
		}
		
		if (index == -1) {
			System.out.println("Not found");
			return;
		}
		
		Contacts updatedContact = myContacts.get(index);
		
		switch(selection) {
			case 1:{
				updatedContact.setFirstName(update);
				break;
			}
			
			case 2:{
				updatedContact.setLastName(update);
				break;
			}
			
			case 3:{
				updatedContact.setPhoneNumber(update);
				break;
			}
			
			case 4:{
				updatedContact.setContactAddress(update);
				break;
			}
			
			default:{
				deleteContact(myContacts.elementAt(index));
				addContact(updatedContact);
			}
		}
		
	}
	
	public void updatedContact(String ID, String fName, String lName, String pNumber, String contactAddress) {
		if (ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Invalid contact ID.");
		}
		
		if (myContacts.isEmpty()) {
			throw new IllegalArgumentException("No Contacts Available.");
		}
		
		int index = -1;
		
		for (Contacts c: myContacts) {
			if (c.getContactID() == ID) {
				index = myContacts.indexOf(c);
			}
		}
		
		if (index == -1) {
			System.out.println("Not found");
			return;
		}
		
		Contacts tempContact = myContacts.get(index);
		
		tempContact.setFirstName(fName);
		tempContact.setFirstName(lName);
		tempContact.setFirstName(pNumber);
		tempContact.setFirstName(contactAddress);
		
		myContacts.remove(index);
		myContacts.add(tempContact);
	}
	
	public String generateUniqueId() {
		Random rand = new Random();
		int newID = rand.nextInt(1000000000);
		String uniqueID = Integer.toString(newID);
		
		for (Contacts c: myContacts) {
			while(c.getContactID() == uniqueID) {
				newID = rand.nextInt(1000000000);
				uniqueID = Integer.toString(newID);
			}
		}
		
		System.out.println("New Contact ID created: " + uniqueID);
		return uniqueID;
	}
}
