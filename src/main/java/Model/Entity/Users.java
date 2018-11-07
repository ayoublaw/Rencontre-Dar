package Model.Entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    public static enum Etat{
        Actif,Suspendu
    }
    public static enum Roles{
        Utilisateur,Admin
    }

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

    @Column
    private String email;


    @Enumerated(EnumType.STRING)
    @Column
    private Etat etat;

    @Enumerated(EnumType.STRING)
    @Column
    private Roles role;


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

    public List<Evenement_Participant> getUser_participation() {
        return user_participation;
    }

    public void setUser_participation(List<Evenement_Participant> user_participation) {
        this.user_participation = user_participation;
    }

    public List<Evenement> getEvenement_create() {
        return Evenement_create;
    }

    public void setEvenement_create(List<Evenement> evenement_create) {
        Evenement_create = evenement_create;
    }

    public List<SignalCompte> getUsers_signals() {
        return users_signals;
    }

    public void setUsers_signals(List<SignalCompte> users_signals) {
        this.users_signals = users_signals;
    }

    public List<CentreInt> getCentreInteret() {
        return CentreInteret;
    }

    public void setCentreInteret(List<CentreInt> centreInteret) {
        CentreInteret = centreInteret;
    }

    public List<SignalCompte> getCreated_Signal() {
        return Created_Signal;
    }

    public void setCreated_Signal(List<SignalCompte> created_Signal) {
        Created_Signal = created_Signal;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
