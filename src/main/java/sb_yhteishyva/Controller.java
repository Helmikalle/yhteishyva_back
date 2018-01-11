package sb_yhteishyva;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/test")
public class Controller {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private KaupunkiRepository kaupunkiRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody
    String addNewSchool(@RequestParam String korkeakoulu
            , @RequestParam double kaikkiHakijat, @RequestParam double ensisijaisetHakijat,
                        @RequestParam double paikanVastaanottaneet) {
        Opiskelupaikka n = new Opiskelupaikka();
        n.setKorkeakoulu(korkeakoulu);
        n.setKaikkiHakijat(kaikkiHakijat);
        n.setEnsisijaisetHakijat(ensisijaisetHakijat);
        n.setPaikanVastaanottaneet(paikanVastaanottaneet);
        schoolRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Opiskelupaikka> getAllUsers() {
        return schoolRepository.findAll();
    }

    @RequestMapping(path = "/addCity")
    public @ResponseBody
    String addNewCity(@RequestParam long kaupunki,@RequestParam long id){
        Opiskelupaikka o;
        Kaupunki k;
        k = kaupunkiRepository.findOne(kaupunki);
        k.getOpiskelupaikat().add(schoolRepository.findOne(id));
        o = schoolRepository.findOne(id);
        o.getKaupungit().add(k);
        schoolRepository.save(o);
        kaupunkiRepository.save(k);
        return "saved";
    }
}
