package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SignalCompte")
public class SignalCompte implements Serializable {

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String Name;


    @ManyToOne
    private Users user_create_signal;


    @ManyToOne
    private Users user_signal;

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



}
