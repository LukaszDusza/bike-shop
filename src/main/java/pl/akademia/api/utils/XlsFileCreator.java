package pl.akademia.api.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XlsFileCreator<T> {

    private Class<T> clazz;

    public XlsFileCreator(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void createFile(List<T> series, String path, String fileName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        //tworzę obiekt reprezentujący cały plik excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        // tworzę arkusz w pliku
        HSSFSheet sheet = workbook.createSheet(fileName);

        //ustawiam czcionki dla nagłówka
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        //zapisuję styl czcionkek do do głównego obiektu
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        //kolekcja zawierająca nazwy kolumn odczytanych z pól klasy podstawionej pod typ T
        List<String> columns = new ArrayList<>();

        //iteracja po klasie przekazanej do pola 'clazz'. Odczytuję wszystkie zadeklarowane pola.
        for (Field f : clazz.getDeclaredFields()) {
            columns.add(f.getName());
        }

        //zapisuję do struktury pliku excel odczytane powyżej pola klasy jako nagłówki kolumn.
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
            cell.setCellStyle(headerCellStyle);
        }

        // test odczytu metod zaczynających się na 'get'. Tylko test.
        columns.forEach(t -> System.out.println("get" + t.substring(0, 1).toUpperCase() + t.substring(1)));


        //zapis danych i wywoływanie metod 'get'.
        for (int i = 0; i < series.size(); i++) {

            //plus 1, ponieważ pierwszy wiersz, czyli index 0 w dokumencie zarezerwowany na nagłówki.
            HSSFRow row = sheet.createRow(i + 1);

            for (int j = 0; j < columns.size(); j++) {

                HSSFCell cell = row.createCell(j);

                //refleksja - odczyt metod 'get' jak powyżej w teście
                Method method = series.get(i)
                        .getClass()
                        .getMethod("get" + columns.get(j)
                                .substring(0, 1)
                                .toUpperCase() + columns.get(j)
                                .substring(1));

                //wywolywanie wszystkich dostępnych metod 'get' na obiekcie i zapis do arkusza.
                Object result = method.invoke(series.get(i));
                cell.setCellValue(String.valueOf(result));
            }
        }

        //stylizacja arkusza - ustawianie auto szerokości kolumn.
        for (int i = 0; i < columns.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        //dodawanie unikalnej nazwy do pliku. Przykład: persons_12948573628.xls
//        long mills = System.currentTimeMillis();
//        String file = path + fileName + "_" + mills + ".xls";
        String file = path + fileName;

        //zapis pliku
        workbook.write(new File(file));
        workbook.close();
    }

}
