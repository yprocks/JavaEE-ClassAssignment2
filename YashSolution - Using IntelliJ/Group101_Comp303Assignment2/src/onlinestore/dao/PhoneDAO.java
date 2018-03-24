package onlinestore.dao;

import onlinestore.model.Phone;

import java.util.List;

public interface PhoneDAO {
	public void addPhone(Integer key, Phone item);

	public List<Phone> getPhone();

	public Phone getPhoneItem(Integer key);
}
