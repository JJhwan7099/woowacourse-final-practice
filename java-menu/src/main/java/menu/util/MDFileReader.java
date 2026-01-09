package menu.util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MDFileReader {
    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();

        // getResourceAsStream을 사용하면 JAR 내부나 채점 서버 환경에서도 파일을 안전하게 읽어옵니다.
        try (InputStream inputStream = MDFileReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(
                     Objects.requireNonNull(inputStream, "[ERROR] 파일을 찾을 수 없습니다: " + fileName),
                     StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line.trim());
                }
            }
        } catch (Exception e) {
            // 예외 발생 시 로그를 남기거나 예외를 던져 프로그램이 오작동하는 것을 방지합니다.
            throw new IllegalArgumentException("[ERROR] 파일 읽기 중 오류가 발생했습니다: " + fileName);
        }
        return lines;
    }
}
