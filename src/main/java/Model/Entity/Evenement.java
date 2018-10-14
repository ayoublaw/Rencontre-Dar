package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Evenement")
public class Evenement implements Serializable {

    public static enum Etat {
        Invitation,AttendAcceptation,Complet,Expirer
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String Description;

    @Column
    private int NbrParticipant;

    @Column
    private int Lieu;

    @Column
    private Etat etat;

    @Enumerated(EnumType.STRING)
    @Column
    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @ManyToOne
    private Users user_create;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "evenement")
    private List<Evenement_Participant> users_participate;


    public List<Evenement_Participant> getUsers_participate() {
        return users_participate;
    }

    public void setUsers_participate(List<Evenement_Participant> users_participate) {
        this.users_participate = users_participate;
    }

    public Users getUser_create() {
        return user_create;
    }

    public void setUser_create(Users user_create) {
        this.user_create = user_create;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getNbrParticipant() {
        return NbrParticipant;
    }

    public void setNbrParticipant(int nbrParticipant) {
        NbrParticipant = nbrParticipant;
    }

    public int getLieu() {
        return Lieu;
    }

    public void setLieu(int lieu) {
        Lieu = lieu;
    }






}
