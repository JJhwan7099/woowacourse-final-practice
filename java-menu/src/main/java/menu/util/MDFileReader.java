package menu.util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MDFileReader {
    public static List<String> readFile(String fileName) throws URISyntaxException {
        List<String> lines = new ArrayList<>();
        try {
            //파일 객체 생성
            File file = new File(String.valueOf(Paths.get(MDFileReader.class.getClassLoader().getResource(fileName).toURI())));
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                lines.add(line.trim());
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일이 없음");
        } catch (IOException e) {
            System.out.println(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
