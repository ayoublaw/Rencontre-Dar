package Model.Entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private int Id;

    @Column
    @Expose
    private String Nom;

    @Column
    @Expose
    private int Numero;

    @Column
    @Expose
    private String Rue;

    @Column
    @Expose
    private String Ville;

    @Column
    @Expose
    private int CodePostal;

    @ManyToOne
    @JoinColumn(name="User")
    private Users user;

    public Address() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int codePostal) {
        CodePostal = codePostal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getRue() {
        return Rue;
    }

    public void setRue(String rue) {
        Rue = rue;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
