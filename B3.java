import java.util.ArrayList;
import java.util.List;

public class StringExtractor {
    public static List<String> extractStrings(String sourceCode) {
        List<String> extractedStrings = new ArrayList<>();
        
        StringBuilder stringBuilder = new StringBuilder();
        boolean isInString = false;
        boolean isEscaped = false;
        
        for (char c : sourceCode.toCharArray()) {
            if (!isInString && c == '"') {
                isInString = true;
            } else if (isInString) {
                if (isEscaped) {
                    stringBuilder.append(c);
                    isEscaped = false;
                } else if (c == '\\') {
                    isEscaped = true;
                } else if (c == '"') {
                    isInString = false;
                    extractedStrings.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                } else {
                    stringBuilder.append(c);
                }
            }
        }
        
        return extractedStrings;
    }

    public static void main(String[] args) {
        String sourceCode = "一般学习一门编程语言的时候，基本上第一个程序是输出\\\"Hello World!\\\"";
        
        List<String> extractedStrings = extractStrings(sourceCode);
        for (String str : extractedStrings) {
            System.out.println(str);
        }
    }
}
