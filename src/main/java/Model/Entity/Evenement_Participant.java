package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Evenement_Participant")
public class Evenement_Participant implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user_participate;

    @ManyToOne
    @JoinColumn(name = "evenemrnt")
    private Evenement evenement;

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Users getUser_participate() {
        return user_participate;
    }

    public void setUser_participate(Users user_participate) {
        this.user_participate = user_participate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
