import com.google.common.base.Strings;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {

  private String delimiter;
  private String numbers;

  private StringCalculator(String delimiter, String numbers) {
    this.delimiter = delimiter;
    this.numbers = numbers;
  }


  private int sum() {
    noNegativeNumbers();
    return getNumbers().sum();
  }

  private void noNegativeNumbers() {
    String negativeNumberSequence = getNumbers().filter(n -> n < 0)
        .mapToObj(Integer::toString)
        .collect(Collectors.joining(","));
    if (!negativeNumberSequence.isEmpty()) {
      throw new IllegalArgumentException("negative number: " + negativeNumberSequence);
    }
  }

  private IntStream getNumbers() {
    if (Strings.isNullOrEmpty(numbers)) {
      return IntStream.empty();
    } else {
      return Stream.of(numbers.split(delimiter))
          .filter(i -> !Strings.isNullOrEmpty(i))
          .filter(i -> !"null".equalsIgnoreCase(i))
          .mapToInt(Integer::parseInt)
          .map(n -> n % 1000);
    }
  }

  public static int Add(String input) {
    if (input == null) {
      return 0;
    }
    return parseInput(input).sum();
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
