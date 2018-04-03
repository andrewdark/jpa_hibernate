package ua.pp.darknsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "contact_tel_detail")
public class ContactTelDetail {

  private int id;
  private int version;
  private String telType;
  private String telNumber;
  private Contact contact;

  public ContactTelDetail() {
  }

  public ContactTelDetail(String telType, String telNumber) {
    this.telType = telType;
    this.telNumber = telNumber;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Version
  @Column(name = "version")
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Column(name = "tel_type")
  public String getTelType() {
    return telType;
  }

  public void setTelType(String telType) {
    this.telType = telType;
  }

  @Column(name = "tel_number")
  public String getTelNumber() {
    return telNumber;
  }

  public void setTelNumber(String telNumber) {
    this.telNumber = telNumber;
  }
@ManyToOne
@JoinColumn(name = "contact_id")
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Override
  public String toString() {
    return "ContactTelDetail{" +
        "id=" + id +
        ", version=" + version +
        ", telType='" + telType + '\'' +
        ", telNumber='" + telNumber + '\'' +
        '}';
  }
}
