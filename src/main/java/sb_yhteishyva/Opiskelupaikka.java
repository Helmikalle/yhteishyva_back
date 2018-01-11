package sb_yhteishyva;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Opiskelupaikka {

    private @Id @GeneratedValue long id;
    private String korkeakoulu;
    private double ensisijaisetHakijat;
    private double kaikkiHakijat;
    private double paikanVastaanottaneet;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name="koulu_kaupunki",
            joinColumns=@JoinColumn(name="KORKEAKOULU_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="KAUPUNKI_ID", referencedColumnName="ID"))
    private Collection<Kaupunki> kaupungit = new ArrayList<>();

    public Opiskelupaikka() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKorkeakoulu() {
        return korkeakoulu;
    }

    public void setKorkeakoulu(String korkeakoulu) {
        this.korkeakoulu = korkeakoulu;
    }

    public double getEnsisijaisetHakijat() {
        return ensisijaisetHakijat;
    }

    public void setEnsisijaisetHakijat(double ensisijaisetHakijat) {
        this.ensisijaisetHakijat = ensisijaisetHakijat;
    }

    public double getKaikkiHakijat() {
        return kaikkiHakijat;
    }

    public void setKaikkiHakijat(double kaikkiHakijat) {
        this.kaikkiHakijat = kaikkiHakijat;
    }

    public double getPaikanVastaanottaneet() {
        return paikanVastaanottaneet;
    }

    public void setPaikanVastaanottaneet(double paikanVastaanottaneet) {
        this.paikanVastaanottaneet = paikanVastaanottaneet;
    }

    public Collection<Kaupunki> getKaupungit() {
        return kaupungit;
    }

    public void setKaupungit(Collection<Kaupunki> kaupungit) {
        this.kaupungit = kaupungit;
    }
}


