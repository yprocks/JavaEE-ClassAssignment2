package dao;

import model.Phone;

import java.util.List;

public interface PhoneDAO
{
    List<Phone> getAllPhone();

    void addPhone(Integer key, Phone phone);
}
