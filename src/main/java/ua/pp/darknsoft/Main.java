package ua.pp.darknsoft;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.pp.darknsoft.config.AppConfig;
import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.ContactTelDetail;
import ua.pp.darknsoft.entity.Hobby;
import ua.pp.darknsoft.service.ContactService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

    ContactService contactService = (ContactService) context.getBean("jpaContactService");
    //System.out.println(contactService.findAll()==null);
    //getAll(contactService.findAll());
    List<Contact> contactList = contactService.findByCriteriaQuery("Valentin",null);
    listContactsWithDetail(contactList);

    DataSource dt = (DataSource) context.getBean("getDataSource");
    try {
      dt.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void getAll(List<Contact> contacts) {
    System.out.println("#######");
    for (Contact contact : contacts) {
      System.out.println(contact);
      System.out.println("--------------------------------------");
    }
  }

  private static void listContactsWithDetail(List<Contact> contacts) {

    System.out.println("Listing contacts with details: ");
    for (Contact contact :  contacts) {
      System.out.println(contact);
      if (contact.getContactTelDetails() != null) {
        for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
          System.out.println(contactTelDetail);

        }
      }
      if (contact.getHobbies() != null) {
        for (Hobby hobby : contact.getHobbies()) {
          System.out.println(hobby);
        }
      }
      System.out.println();
    }
  }

}
