import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class StringCalculatorTest {

  @Test
  public void addEmptyStringTo0() {
    int sum = StringCalculator.Add("");
    assertThat(sum, is(0));
  }

  @Test
  public void addSingleNumberToItself() {
    assertThat(StringCalculator.Add("72"), is(72));
    assertThat(StringCalculator.Add("52"), is(52));
  }

  @Test
  public void addTwoNumbersSeparatedByComma() {
    assertThat(StringCalculator.Add("1,5"), is(6));
    assertThat(StringCalculator.Add("1,7"), is(8));
  }

  @Test
  public void addThreeNumbersSeparatedByComma() {
    assertThat(StringCalculator.Add("1,2,4"), is(7));
  }
}