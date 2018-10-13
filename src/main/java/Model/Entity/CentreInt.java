package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "CentreInteret")
public class CentreInt implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String Name;


    @ManyToOne
    private Users users;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


}
