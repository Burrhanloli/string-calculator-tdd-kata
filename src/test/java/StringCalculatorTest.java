import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

  @Test
  public void addNumbersSeparatedByCommaWithNullString() {
    assertThat(StringCalculator.Add("1,null"), is(1));
    assertThat(StringCalculator.Add("1,3,null"), is(4));
  }

  @Test
  public void addNumbersSeparatedByCommaWithEmptyString() {
    assertThat(StringCalculator.Add("1,"), is(1));
    assertThat(StringCalculator.Add("1,3,"), is(4));
  }

  @Test
  public void addNullTo0() {
    assertThat(StringCalculator.Add(null), is(0));
  }

  @Test
  public void addNumbersDelimitedByNewline() {
    assertThat(StringCalculator.Add("1\n10"), is(11));
  }

  @Test
  public void addNumbersDelimitedByCommaOrNewline() {
    assertThat(StringCalculator.Add("1,21\n30"), is(52));
  }

  @Test
  public void usesDelimiterSepcified() {
    assertThat(StringCalculator.Add("//;\n1;40"), is(41));
    assertThat(StringCalculator.Add("//:\n2:3:4:1"), is(10));
    assertThat(StringCalculator.Add("//.\n2.3.4.1"), is(10));
  }

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void throwsOnNegativeNumber() {
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("negative number: -7");

    StringCalculator.Add("-7");
  }

  @Test
  public void throwsOnNegativeNumbersWithAllNumbersInExceptionMessage() {
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("negative number: -7,-15,-53");

    StringCalculator.Add("1,-7,5,-15,-53");
  }


  @Test
  public void mapsNumbersAbove1000ToLastThreeDigits() {
    assertThat(StringCalculator.Add("1002"), is(2));
    assertThat(StringCalculator.Add("1040,10002"), is(42));
  }

  @Test
  public void acceptsDelimiterOfAnyLength() {
    assertThat(StringCalculator.Add("//[***]\n1***2***3"), is(6));
  }

  @Test
  public void acceptsMultipleDelimiters() {
    assertThat(StringCalculator.Add("//[-][;]\n11-2;2"), is(15));
    assertThat(StringCalculator.Add("//[--][...]\n21--32...40"), is(93));
  }

}