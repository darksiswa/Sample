package com.ksi;

public class KSI2 extends SimpleCipher {

    private boolean addLetterSpace;

    public KSI2(boolean addLetterSpace) {
        this.addLetterSpace = addLetterSpace;
    }
    private String[] baconAlphabet = {
        "AAAAA",
        "AAAAB",
        "AAABA",
        "AAABB",
        "AABAA",
        "AABAB",
        "AABBA",
        "AABBB",
        "ABAAA",
        "BBBAA",
        "ABAAB",
        "ABABA",
        "ABABB",
        "ABBAA",
        "ABBAB",
        "ABBBA",
        "ABBBB",
        "BAAAA",
        "BAAAB",
        "BAABA",
        "BAABB",
        "BBBAB",
        "BABAA",
        "BABAB",
        "BABBA",
        "BABBB"
    };

    @Override
    public String encrypt(String plainText) {
        String s = "";
        for (char c : plainText.toCharArray()) {
            int i = this.getValidCharacterIndex(c);
            if (i > -1) {
                s += baconAlphabet[i];
                if (addLetterSpace) {
                    s += " ";
                }
            } else {
                s += c;
            }
        }
        return s;
    }

    @Override
    public String decrypt(String encrypted) {
        String s = "";
        char[] characters = encrypted.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            if (characters[i] == 'A' && (i + 4) < characters.length || characters[i] == 'B' && (i + 4) < characters.length) {
                boolean flag = false;
                String t = "";

                for (int x = i; x < i + 5; x++) {

                    if (characters[x] != 'A' && characters[x] != 'B') {

                        flag = true;
                    }
                    t += characters[x];
                }
                if (!flag) {

                    for (int x = 0; x < baconAlphabet.length; x++) {
                        if (t.equals(baconAlphabet[x])) {
                            s += this.getValidCharacterForIndex(x);
                            i += addLetterSpace ? 5 : 4;
          
                            continue;
                        }
                    }
                }
         

            } else {
                
                s += characters[i];
            }
        }
        return s;
    }
    
 public static void main(String[] args) { 
	    
	    String name = "salah satu kisah 123";
	    String temp1,temp2;
	    KSI2 temp = new KSI2(true);
	    temp1 = temp.encrypt(name);	    
	    temp2 = temp.decrypt(temp1);
	    System.out.println("Plaintext " + name + "!");
	    System.out.println("Encrypt " + temp1 + "!");
	    System.out.println("Decrypt " + temp2 + "!");
	  }
}