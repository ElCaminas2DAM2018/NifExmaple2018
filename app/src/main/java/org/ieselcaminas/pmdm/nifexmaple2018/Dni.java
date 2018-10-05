package org.ieselcaminas.pmdm.nifexmaple2018;

public class Dni {
	private int number;
	private char letter;
	private static char[] letters = {'T','R','W','A','G','M','Y','F','P','D',
		'X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	public Dni() {
		number = 0;
		letter = letters[0];
	}
	public Dni(int newNumber) throws NIFException {
		if (newNumber<=0) {
			number=-newNumber;
			throw new NIFException();
		}
		number=Math.abs(newNumber);
		letter = letters[number%23];
	}
	public Dni(int newNumber, char newLetter) throws NIFLetterException {
		number=Math.abs(newNumber);
		letter = letters[number%23];
		//System.out.println("Letra mayuscula: "+Character.toUpperCase(newLetter));
		if (Character.toUpperCase(newLetter)!=letter) {
			number=-newNumber;
			throw new NIFLetterException();
		}
	}
	public Dni(String s) throws NIFLetterException, NIFException{
		char c = s.charAt(s.length()-1);
		String newS =s.replaceAll("[^0-9]", "");
		try {
			number = Integer.parseInt(newS);
		} catch(NumberFormatException ex) {
			number=-1;
			throw new NIFException();
		}
		letter = letters[number%23];
		if (Character.toUpperCase(c)!=letter) {
			throw new NIFLetterException(); 
		}
	}
	public boolean isDniCorrect() {
		if (number<0)
			return false;
		else
			return true;
	} 
	public int getNumber() {
		return number;
	}
	public char getLetter() {
		return letter;
	}
	public void setNumber(int newNumber) throws NIFException {
		if (newNumber<=0) {
			throw new NIFException();
		}
		number = newNumber;
		letter = letters[number%23];
	}
	public String toString() {
		return number+ Character.toString(letter);
	}
	public String toFormattedString() {
		StringBuilder s = new StringBuilder(String.valueOf(number));
		if (number>999) {
			s.insert(s.length()-3,'.');
		}
		if (number>999999) {
			s.insert(s.length()-7,'.');
		}
		if (number>999999999) {
			s.insert(s.length()-11,'.');
		}
		return s.toString()+"-"+letter;
	}
}
