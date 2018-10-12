package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Evenement")
public class Evenement implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String Description;

    @Column
    private int NbrParticipant;

    @ManyToOne
    private Users user_create;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "evenement")
    private List<Evenement_Participant> users_participate;





}
