package test.errorreporting;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.Test;

import test.UsagePatternTestingFramework;
import test.assertions.Assertions;

public class ErrorCountTest extends UsagePatternTestingFramework {

	@Test
	public void CipherPredicateCountTest1() throws GeneralSecurityException {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(128);
		SecretKey key = keygen.generateKey();
		Cipher cCipher = Cipher.getInstance("AES");
		cCipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encText = cCipher.doFinal("".getBytes());
		Assertions.notHasEnsuredPredicate(encText);
		Assertions.predicateErrors(0);
		Assertions.constraintErrors(1);
		cCipher.getIV();
	}

	@Test
	public void CipherPredicateCountTest2() throws GeneralSecurityException {
		KeyGenerator keygen = KeyGenerator.getInstance("DES");
		keygen.init(128);
		SecretKey key = keygen.generateKey();
		Cipher cCipher = Cipher.getInstance("AES/CBC");
		cCipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encText = cCipher.doFinal("".getBytes());
		Assertions.notHasEnsuredPredicate(encText);
		Assertions.notHasEnsuredPredicate(key);
		Assertions.predicateErrors(1);
		Assertions.constraintErrors(1);
		cCipher.getIV();
	}

	@Test
	public void CipherPredicateCountTest3() throws GeneralSecurityException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(129);
		SecretKey key = keyGen.generateKey();

		String algorithmTransformation = "AES/CBC";
		Cipher c = Cipher.getInstance(algorithmTransformation);
		c.init(1, key);
		byte[] ciphertext = c.doFinal("WHAT!?".getBytes());
		Assertions.notHasEnsuredPredicate(ciphertext);
		Assertions.notHasEnsuredPredicate(key);
		Assertions.predicateErrors(1);
		Assertions.constraintErrors(1);

	}
}
