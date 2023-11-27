package util;
import java.util.ArrayList;
import java.util.List;

public class CPF {

  private static final short CPF_VERIFICATION_REMAINDER_VALUE = 11;
  private static final List<Integer> CPF_VERIFICATION_DIGIT_INDICES = List.of(9,10);

  private final List<Integer> digits;


  public CPF(String cpf) {
    if (cpf == null) {
      throw new IllegalArgumentException("O CPF fornecido é inválido. O CPF não pode ser nulo.");
    }
    this.digits = extractDigitsFromCPF(cpf);
    if (digits.size() != 11) {
      throw new IllegalArgumentException("O CPF fornecido é inválido. Ele deve conter exatamente 11 dígitos numéricos. Os formatos aceitos são xxx.xxx.xxx-xx ou xxxxxxxxxxx.");
    }
  }

  private List<Integer> extractDigitsFromCPF(String cpf) {

    List<Integer> extractedDigits = new ArrayList<>();
    
    for (char currentDigit : cpf.toCharArray()) {
      if (Character.isDigit(currentDigit)) {
        extractedDigits.add(Character.getNumericValue(currentDigit));
      }
    }

    return extractedDigits;
  }

  public boolean isValid() {
    int currentDigit;

    for (Integer index : CPF_VERIFICATION_DIGIT_INDICES) {
      currentDigit = getVerificationDigit(index);
      if (!digits.get(index).equals(currentDigit)) {
        return false;
      }
    }
    return true;
  }

  private int getVerificationDigit(int digitIndex) {
    int sumOfWeightedDigits = 0;
    int weight = digitIndex+1;

    for (short i = 0; i < digitIndex; i++) {
      int currentDigit = digits.get(i);
      sumOfWeightedDigits += currentDigit * weight;
      weight -= 1;
    }

    int remainder = CPF_VERIFICATION_REMAINDER_VALUE - (sumOfWeightedDigits % CPF_VERIFICATION_REMAINDER_VALUE);
    return remainder >= CPF_VERIFICATION_REMAINDER_VALUE-1 ? 0 : remainder;
  }

  @Override
  public String toString() {
    return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", digits.toArray());
  }
}
