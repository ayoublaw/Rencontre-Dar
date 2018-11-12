package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Evenement_Participant")
public class Evenement_Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Users user_participate;

    @ManyToOne
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
