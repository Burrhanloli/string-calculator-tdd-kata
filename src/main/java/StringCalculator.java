import com.google.common.base.Strings;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {

  private String delimiter;
  private String numbers;

  private StringCalculator(String delimiter, String numbers) {
    this.delimiter = delimiter;
    this.numbers = numbers;
  }


  public static void main(String[] args) {

  }

  public static int Add(String numbers) {
    if (Strings.isNullOrEmpty(numbers)) {
      return 0;
    } else {
      StringCalculator calculator = parseInput(numbers);
      return Stream.of(calculator.numbers.split(calculator.delimiter))
          .filter(i -> !Strings.isNullOrEmpty(i))
          .filter(i -> !"null".equalsIgnoreCase(i))
          .mapToInt(Integer::parseInt)
          .sum();
    }
  }

  private static StringCalculator parseInput(String input) {
    if (input.startsWith("//")) {
      String[] headerAndNumberSequence = input.split("\n", 2);
      String delimiter = parseDelimiter(headerAndNumberSequence[0]);
      return new StringCalculator(delimiter, headerAndNumberSequence[1]);
    } else {
      return new StringCalculator(",|\n", input);
    }
  }

  private static String parseDelimiter(String header) {
    return header.substring(2);
  }
}
