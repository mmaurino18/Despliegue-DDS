package dominio;


import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;

public class ValidadorContraseñas {
    private final org.passay.PasswordValidator validator;

    public ValidadorContraseñas() {
        LengthRule lengthRule = new LengthRule(8, 30);
        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 1);
        validator = new org.passay.PasswordValidator(lengthRule, upperCaseRule, lowerCaseRule, digitRule, specialCharRule);
    }
    public boolean validar(String password) {
        org.passay.RuleResult result = validator.validate(new org.passay.PasswordData(password));
        return result.isValid();
    }
}
