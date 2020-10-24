package by.belhard.project;

import org.apache.commons.codec.digest.DigestUtils;

public class GameApplication {
    public static void main(String[] args) {
        String s1 = "123";
        String s1hex = DigestUtils.sha256Hex(s1);
        System.out.println(s1hex);
        System.out.println(s1hex.length());
    }



}
