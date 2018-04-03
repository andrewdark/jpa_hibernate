package ua.pp.darknsoft.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContactTelDetail.class)
public class ContactTelDetail_ {
  public static volatile SingularAttribute<ContactTelDetail,Integer> id;
  public static volatile SingularAttribute<ContactTelDetail,String> telType;
  public static volatile SingularAttribute<ContactTelDetail,String> telNumber;


}
