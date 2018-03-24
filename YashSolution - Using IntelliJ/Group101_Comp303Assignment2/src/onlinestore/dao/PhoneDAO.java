package onlinestore.dao;

import onlinestore.model.Phone;

import java.util.List;

public interface PhoneDAO {
	void addPhone(Integer key, Phone item);

	List<Phone> getPhone();

	Phone getPhoneItem(Integer key);
}
