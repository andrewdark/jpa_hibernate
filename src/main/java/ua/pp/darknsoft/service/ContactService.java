package ua.pp.darknsoft.service;

import ua.pp.darknsoft.entity.Contact;

import java.util.List;

public interface ContactService {

  List<Contact> findAll();

  List<Contact> findAllWithDetail();

  Contact findById(int id);

  Contact save(Contact contact);

  void delete(Contact contact);

  List<Contact> findByCriteriaQuery(String firstName, String lastName);

}
