package Model.Entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Evenement")
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    public static enum Etat {
        Invitation,AttendAcceptation,Complet,Expirer
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column
    @Expose
    private String Description;

    @Column
    @Expose
    private int NbrParticipant;

    @Column
    @Expose
    private String Lieu;

    public String getAdr_Proposé() {
        return Adr_Proposé;
    }

    public void setAdr_Proposé(String adr_Proposé) {
        Adr_Proposé = adr_Proposé;
    }

    @Column
    @Expose
    private String Adr_Proposé;

    @Column
    @Expose
    private Date date;


    @Enumerated(EnumType.STRING)
    @Column
    @Expose
    private Etat etat;

    @ManyToOne
    @Expose
    private Users user_create;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "evenement")
    @Expose
    private List<Evenement_Participant> users_participate;

    @ElementCollection
    @Column
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> LieuProposé;

    @ManyToOne
    @Expose
    private CentreInt centreInt;

    public List<String> getLieuProposé() {
        return LieuProposé;
    }

    public void setLieuProposé(List<String> lieuProposé) {
        LieuProposé = lieuProposé;
    }


    public CentreInt getCentreInt() {
        return centreInt;
    }

    public void setCentreInt(CentreInt centreInt) {
        this.centreInt = centreInt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

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

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }






}
