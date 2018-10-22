package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SignalCompte")
public class SignalCompte implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String Name;


    @ManyToOne
    @JoinColumn(name="User_created")
    private Users user_create_signal;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Users getUser_create_signal() {
        return user_create_signal;
    }

    public void setUser_create_signal(Users user_create_signal) {
        this.user_create_signal = user_create_signal;
    }

    public Users getUser_signal() {
        return user_signal;
    }

    public void setUser_signal(Users user_signal) {
        this.user_signal = user_signal;
    }

    @ManyToOne
    @JoinColumn(name="User_signal")
    private Users user_signal;


}
