package Model.Entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Users implements Serializable {

    public static enum Etat{
        Actif,Suspendu
    }
    public static enum Roles{
        Utilisateur,Admin
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column
    private String sex;


    @Column
    private String password;

    @Column
    private long age;

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
    private List<CentreInt> centreInteret;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_create_signal")
    private List<SignalCompte> created_Signal;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_signal")
    private List<SignalCompte> users_signals;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_create")
    private List<Evenement> evenement_create;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user_participate")
    private List<Evenement_Participant> user_participation;

    public List<Evenement_Participant> getUser_participation() {
        return user_participation;
    }

    public void setUser_participation(List<Evenement_Participant> user_participation) {
        this.user_participation = user_participation;
    }


    public List<SignalCompte> getUsers_signals() {
        return users_signals;
    }

    public void setUsers_signals(List<SignalCompte> users_signals) {
        this.users_signals = users_signals;
    }

    public List<Evenement> getEvenement_create() {
        return evenement_create;
    }

    public void setEvenement_create(List<Evenement> evenement_create) {
        this.evenement_create = evenement_create;
    }

    public List<SignalCompte> getCreated_Signal() {
        return created_Signal;
    }

    public void setCreated_Signal(List<SignalCompte> created_Signal) {
        this.created_Signal = created_Signal;
    }

    public List<CentreInt> getCentreInteret() {
        return centreInteret;
    }

    public void setCentreInteret(List<CentreInt> centreInteret) {
        this.centreInteret = centreInteret;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
