package sb_yhteishyva;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Kaupunki {
    @NotNull @Id @GeneratedValue long id;
    private String kaupunki;
    @ManyToMany (fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "kaupungit")
    private Collection<Opiskelupaikka> opiskelupaikat = new ArrayList<>();

    public Kaupunki() {
    }

    public Kaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }
    @JsonIgnore
    public Collection<Opiskelupaikka> getOpiskelupaikat() {
        return opiskelupaikat;
    }

    public void setOpiskelupaikat(Collection<Opiskelupaikka> opiskelupaikat) {
        this.opiskelupaikat = opiskelupaikat;
    }
}
