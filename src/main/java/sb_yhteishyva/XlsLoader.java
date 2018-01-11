package sb_yhteishyva;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

@Component
public class XlsLoader implements CommandLineRunner {
    private final SchoolRepository schoolRepository;
    private final KaupunkiRepository kaupunkiRepository;
    private static final String FILE_NAME = "tietokanta.xlsx";

    @Autowired
    public XlsLoader(KaupunkiRepository kaupunkiRepository, SchoolRepository schoolRepository) {
        this.kaupunkiRepository = kaupunkiRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.kaupunkiRepository.save(new Kaupunki("Helsinki"));
        this.kaupunkiRepository.save(new Kaupunki("Tampere"));

        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Opiskelupaikka n = new Opiskelupaikka();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        n.setKorkeakoulu(currentRow.getCell(0).getStringCellValue());
//                        System.out.print(currentCell.getStringCellValue() + "--");
                    }
                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        n.setEnsisijaisetHakijat(currentRow.getCell(1).getNumericCellValue());
                        n.setPaikanVastaanottaneet(currentRow.getCell(3).getNumericCellValue());
                        n.setKaikkiHakijat(currentRow.getCell(2).getNumericCellValue());
//                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }
                }
                schoolRepository.save(n);
//                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
