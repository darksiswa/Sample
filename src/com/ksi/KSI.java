package com.ksi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class KSI extends SimpleCipher {
	 
    private char[] key;
 
    public KSI(final String key) {
        this.key = key.toCharArray();
    }
 
    @Override
    public String encrypt(String plainText) {
        int keyshift = 0;
        String encrypted = "";
 
        for (char c : plainText.toCharArray()) {
            int pos = this.getValidCharacterIndex(c);
            if (pos != -1) {
                int keyIndex = this.getValidCharacterIndex(key[keyshift++ % key.length]);
 
                encrypted += this.getValidCharacterForIndex((keyIndex + pos) % 38);
 
            } else {
                encrypted += c;
            }
        }
        return encrypted;
    }
 
    @Override
    public String decrypt(String encrypted) {
        int keyshift = 0;
        String plainText = "";
 
        for (char c : encrypted.toCharArray()) {
            int pos = this.getValidCharacterIndex(c);
            if (pos != -1) {
                int keyIndex = this.getValidCharacterIndex(key[keyshift++ % key.length]);
                int r = (pos - keyIndex) % 38;
                if (r < 0) {
                    r += 38;
                }
                plainText += this.getValidCharacterForIndex(r);
 
            } else {
                plainText += c;
            }
        }
        return plainText;
    }
    
    private static String readFile(String path) throws IOException {
    	  FileInputStream stream = new FileInputStream(new File(path));
    	  try {
    	    FileChannel fc = stream.getChannel();
    	    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
    	    /* Instead of using default, pass in a decoder. */
    	    return Charset.defaultCharset().decode(bb).toString();
    	  }
    	  finally {
    	    stream.close();
    	  }
    	}
    
    public static void main(String[] args) { 
	    
//	    String name = "opennms adalah platform aplikasi network management kelas enterprise pertama yang dikembangkan dibawah model open source. Proyek opennms dimulai pada juli 1999 dan terdaftar di sourceforge pada bulan maret 2000. opennms dapat dibagi menjadi empat bagian utama yaitu manajemen event, manajemen notifikasi, service assurance, dan pengukuran kinerja.";
//	    String temp1,temp2;
//	    KSI temp = new KSI("password");
//	    temp1 = temp.encrypt(name);	    
//	    temp2 = temp.decrypt(temp1);
//	    System.out.println("Plaintext :" + name);
//	    System.out.println("Chipertext :" + temp1);
//	    System.out.println("Decrypt :" + temp2);
    	//String inputkey,inputtext;
    	//System.out.println("Masukan key untuk enkripsi teks (tanpa spasi) : ");	 
    	try{
    		System.out.println("Masukan key untuk dekrpsi teks (tanpa spasi) : ");
    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	    String inputkey = bufferRead.readLine();
     // 	    System.out.println(s);
    	    System.out.println("Masukan nama file yang akan di dekripsi : ");
    	    String textpath = bufferRead.readLine();
    	    String inputtext = readFile(textpath); 
    	    KSI temp = new KSI(inputkey);
    	    String enctext = temp.decrypt(inputtext);
    	    System.out.println("Plaintext : "+enctext);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}

	  }
}