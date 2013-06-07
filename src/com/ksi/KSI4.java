package com.ksi;

public class KSI4 extends SimpleCipher {

    private final int shift;

    public KSI4(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String plainText) {
        String s = "";
        for (char c : plainText.toCharArray()) {
            int i = getValidCharacterIndex(c);

            if (i > -1) {
                int x = (i + shift) % 36;
                s += this.getValidCharacterForIndex(x < 0 ? 36 - Math.abs(x) : x);

            } else {
                s += c;
            }
        }
        return s;
    }

    @Override
    public String decrypt(String encrypted) {
        String s = "";
        for (char c : encrypted.toCharArray()) {
            int i = getValidCharacterIndex(c);
            if (i > -1) {
                int x = (i - shift) % 36;
                s += this.getValidCharacterForIndex(x < 0 ? 36 + x : x);
            } else {
                s += c;
            }
        }
        return s;
    }
public static void main(String[] args) { 
	    
	    String name = "salah satu kisah 123";
	    String temp1,temp2;
	    KSI4 temp = new KSI4(2);
	    temp1 = temp.encrypt(name);	    
	    temp2 = temp.decrypt(temp1);
	    System.out.println("Plaintext " + name + "!");
	    System.out.println("Encrypt " + temp1 + "!");
	    System.out.println("Decrypt " + temp2 + "!");
	  }
}