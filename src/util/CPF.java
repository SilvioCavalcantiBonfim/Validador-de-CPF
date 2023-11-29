package util;
import java.util.List;

public final class CPF {

  private static final short[] CPF_WEIGHT_FOR_FRIST_VERIFICATION_DIGIT = new short[]{10, 9, 8, 7, 6, 5, 4, 3, 2};
  private static final short[] CPF_WEIGHT_FOR_SECOND_VERIFICATION_DIGIT = new short[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

  private final List<Integer> digits;
  
  private final VerificationDigitCalculator verificationDigitCalculator;

  public CPF(String cpf) {
    if (cpf == null) {
      throw new IllegalArgumentException("O CPF fornecido é inválido. O CPF não pode ser nulo.");
    }

    this.digits = Extractor.extractDigits(cpf);
    verificationDigitCalculator = new VerificationDigitCalculator(digits);

    if (digits.size() != 11) {
      throw new IllegalArgumentException("O CPF fornecido é inválido. Ele deve conter exatamente 11 dígitos numéricos. Os formatos aceitos são xxx.xxx.xxx-xx ou xxxxxxxxxxx.");
    }
  }

  public boolean isValid() {
    int digitVerification = verificationDigitCalculator.calculateVerificationDigit(CPF_WEIGHT_FOR_FRIST_VERIFICATION_DIGIT);
    if(digitVerification != digits.get(9)){
      return false;
    }
    digitVerification = verificationDigitCalculator.calculateVerificationDigit(CPF_WEIGHT_FOR_SECOND_VERIFICATION_DIGIT);
    if(digitVerification != digits.get(10)){
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", digits.toArray());
  }
}
