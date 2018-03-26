package dao;

import model.Phone;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PhoneDAOImpl implements PhoneDAO
{
    private List<Phone> sortedPhone= null;
    private Map<Integer, Phone> phone = null;
    private static PhoneDAOImpl thePhone = null;

    synchronized public static PhoneDAOImpl getInstance()
    {
        if (thePhone == null)
        {
            thePhone = new PhoneDAOImpl();
        }
        return thePhone;
    }

    private PhoneDAOImpl()
    {
        phone = new ConcurrentHashMap<Integer, Phone>();
        sortedPhone = new LinkedList<Phone>();
    }

    private void sortedPhones(Phone phone)
    {
        sortedPhone.add(phone);
    }

    public void addPhone(Integer key, Phone item)
    {
        phone.put(key, item );
        sortedPhones(item);
    }

    public List<Phone> getAllPhone()
    {
        return sortedPhone;
    }





}
