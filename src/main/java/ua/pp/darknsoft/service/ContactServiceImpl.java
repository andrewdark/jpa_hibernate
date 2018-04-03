package ua.pp.darknsoft.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.ContactTelDetail;
import ua.pp.darknsoft.entity.ContactTelDetail_;
import ua.pp.darknsoft.entity.Contact_;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service("jpaContactService")
@Transactional
@Repository
public class ContactServiceImpl implements ContactService {

  @PersistenceContext
  private EntityManager emf;

  @Transactional(readOnly = true)
  @Override
  public List<Contact> findAll() {
    List<Contact> contacts = emf.createNamedQuery("Contact.findAll", Contact.class).getResultList();
    return contacts;
  }

  @Override
  public List<Contact> findAllWithDetail() {
    return null;
  }

  @Override
  public Contact findById(int id) {
    return null;
  }

  @Override
  public Contact save(Contact contact) {
    if (contact.getId() == 0) {
      emf.persist(contact);
    } else {
      emf.merge(contact);
    }
    return contact;
  }

  @Override
  public void delete(Contact contact) {
    Contact mergedContact = emf.merge(contact);
    emf.remove(mergedContact);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
    CriteriaBuilder cb = emf.getCriteriaBuilder();
    CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
    Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
    contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
    contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

    Join<Contact, ContactTelDetail> ctd = contactRoot.join(Contact_.contactTelDetails);

    criteriaQuery.select(contactRoot).distinct(true);
    Predicate criteria = cb.conjunction();
    if (firstName != null) {
      Predicate р = cb.equal(contactRoot.get(Contact_.firstName), firstName);
      criteria = cb.and(criteria, р);
    }

    if (lastName != null) {
      Predicate р = cb.equal(contactRoot.get(Contact_.lastName), lastName);
      criteria = cb.and(criteria, р);

    }
    //Дополнительное условие для ассоциации
    Predicate pp = cb.equal(ctd.get(ContactTelDetail_.telNumber), "222-22-25");
    criteria = cb.and(criteria, pp);

    criteriaQuery.where(criteria);
    return emf.createQuery(criteriaQuery).getResultList();
  }
}
