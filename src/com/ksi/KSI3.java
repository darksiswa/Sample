package com.ksi;

public class KSI3 extends SimpleCipher{

    @Override
    public String encrypt(String s){
        String re = "";
        for (char c : s.toCharArray()) {
            int i = getValidCharacterIndex(c);
            if(i > -1){
                re += this.getValidCharacterForIndex(25 - i);
            }
            else{
                re+=c;
            }
        }
        return re;
    }

    @Override
    public String decrypt(String s){
        String re = "";
        for (char c : s.toCharArray()) {
            int i = getValidCharacterIndex(c);
            if(i > -1){
                re += this.getValidCharacterForIndex(Math.abs(i-25));
            }
            else{
                re+=c;
            }
        }
        return re;
    }
    
 public static void main(String[] args) { 
	    
	    String name = "salah satu kisah 123";
	    String temp1,temp2;
	    KSI3 temp = new KSI3();
	    temp1 = temp.encrypt(name);	    
	    temp2 = temp.decrypt(temp1);
	    System.out.println("Plaintext " + name + "!");
	    System.out.println("Encrypt " + temp1 + "!");
	    System.out.println("Decrypt " + temp2 + "!");
	  }
}
