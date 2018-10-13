package Model.Entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(columnNames = {"name","prenom"}))
public class Users implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String Prenom;

    @Column
    private String Sex;


    @Column
    private String Password;

    @Column
    private int  Age;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    private List<Address> address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "users")
    private List<CentreInt> CentreInteret;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_create_signal")
    private List<SignalCompte> Created_Signal;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_signal")
    private List<SignalCompte> users_signals;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_create")
    private List<Evenement> Evenement_create;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_participate")
    private List<Evenement_Participant> user_participation;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
