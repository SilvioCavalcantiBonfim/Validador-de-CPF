package util;
import java.util.List;

public final class CNPJ {

  private static final short[] CNPJ_WEIGHT_FOR_FRIST_VERIFICATION_DIGIT = new short[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
  private static final short[] CNPJ_WEIGHT_FOR_SECOND_VERIFICATION_DIGIT = new short[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
  
  private final List<Integer> digits;
  private final VerificationDigitCalculator verificationDigitCalculator;

  public CNPJ(String cnpj){
    if (cnpj == null) {
      throw new IllegalArgumentException("O CNPJ fornecido é inválido. O CNPJ não pode ser nulo.");
    }
    digits = Extractor.extractDigits(cnpj);
    verificationDigitCalculator = new VerificationDigitCalculator(digits);
    if (digits.size() != 14) {
      throw new IllegalArgumentException("O CNPJ fornecido é inválido. Ele deve conter exatamente 14 dígitos numéricos. Os formatos aceitos são xx.xxx.xxx/xxxx-xx ou xxxxxxxxxxxxxx.");
    }
  }

  public boolean isValid(){
    int digitVerification = verificationDigitCalculator.calculateVerificationDigit(CNPJ_WEIGHT_FOR_FRIST_VERIFICATION_DIGIT);
    if(digitVerification != digits.get(12)){
      return false;
    }
    digitVerification = verificationDigitCalculator.calculateVerificationDigit(CNPJ_WEIGHT_FOR_SECOND_VERIFICATION_DIGIT);
    if(digitVerification != digits.get(13)){
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%d%d.%d%d%d.%d%d%d/%d%d%d%d-%d%d", digits.toArray());
  }

  public static void main(String[] args) {
    CNPJ cnpj = new CNPJ("11.881.663/0001-89");
    CNPJ cnpj1 = new CNPJ("11.881.663/0001-79");
    System.out.println(cnpj.toString()+ " = "+ (cnpj.isValid()? "Valido": "Invalido"));
    System.out.println(cnpj1.toString()+ " = "+ (cnpj1.isValid()? "Valido": "Invalido"));
  }
}
// -00