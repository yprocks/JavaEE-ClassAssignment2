package onlinestore.dao;

import onlinestore.model.Phone;

import java.util.concurrent.ConcurrentHashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class PhoneDAOImpl implements PhoneDAO {
	private static PhoneDAOImpl thePhone = null;
	private Map<Integer, Phone> phone = null;
	private List<Phone> sortedPhone = null;

	synchronized public static PhoneDAOImpl getInstance() {
		if (thePhone == null) {
			thePhone = new PhoneDAOImpl();
		}
		return thePhone;
	}

	private PhoneDAOImpl() {
		phone = new ConcurrentHashMap<Integer, Phone>();
		sortedPhone = new LinkedList<Phone>();
	}

	private void sortPhones(Phone phone) {
		sortedPhone.add(phone);
//		Collections.sort(sortedPhone);
	}

	public void addPhone(Integer key, Phone item) {
		phone.put(key, item);
		sortPhones(item);
	}

	public List<Phone> getPhone() {
		return sortedPhone;
	}

	public Phone getPhoneItem(Integer key) {
		if (phone == null)
			return null;
		return phone.get(key);
	}

}