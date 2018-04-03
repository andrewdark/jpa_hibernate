package ua.pp.darknsoft.entity;

import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.ContactTelDetail;
import ua.pp.darknsoft.entity.Hobby;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Contact.class)
public class Contact_ {
  public static volatile SingularAttribute<Contact, Long> id;
  public static volatile SetAttribute<Contact, ContactTelDetail>  contactTelDetails;
  public static volatile SingularAttribute<Contact, String> lastName;
  public static volatile SingularAttribute<Contact, Date> birthDate;
  public static volatile SetAttribute<Contact, Hobby> hobbies;
  public static volatile SingularAttribute<Contact, String> firstName;
  public static volatile SingularAttribute<Contact, Integer> version;

}
