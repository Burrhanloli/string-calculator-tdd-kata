import com.google.common.base.Strings;
import java.util.stream.Stream;

public class StringCalculator {

  public static void main(String[] args) {

  }

  public static int Add(String numbers) {
    if (Strings.isNullOrEmpty(numbers)) {
      return 0;
    } else {
      return Stream.of(numbers.split(","))
          .filter(i -> !Strings.isNullOrEmpty(i) || "null".equalsIgnoreCase(i))
          .mapToInt(Integer::parseInt)
          .sum();
    }
  }
}
