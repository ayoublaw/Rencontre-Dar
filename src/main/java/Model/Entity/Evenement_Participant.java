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

}
