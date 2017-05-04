package com.aquarapid.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    /**
     * Creates an md5 hash string from the string input
     *
     * @param s  String to be hashed
     * @return
     */
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Check if a email is valid
     */
    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    /**
     * Check if a cif is valid
     */
    public static boolean isCIF(String cif) {

        if (cif.length() == 9) {

            char letra = cif.charAt(0);
            if (Character.isLetter(letra)) {

                String nums = cif.substring(1, 8);

                int sumEven = 0;
                int sumOdds = 0;

                for (int i = 0; i < nums.length(); i++) {

                    char num = nums.charAt(i);
                    if (Character.isDigit(num)) {

                        int digit = Integer.parseInt("" + num);
                        if (i % 2 != 0) {

                            sumEven += digit;
                        } else {

                            digit = digit * 2;
                            String digitAsStr = "" + digit;
                            for (int j = 0; j < digitAsStr.length(); ++j) {

                                sumOdds += Integer.parseInt("" + digitAsStr.charAt(j));
                            }

                        }
                    } else {

                        //If it's not a digit, we abort.
                        return false;
                    }
                }

                int totalSum = sumEven + sumOdds;
                int lastDigit = totalSum % 10;
                int pos;
                if (lastDigit != 0) {

                    pos = 10 - lastDigit;
                } else {

                    pos = 0;
                }
                int[] controlLetters = {'J', 'A', 'B', 'C', 'D', 'E', 'H', 'I'};

                char controlChar = cif.charAt(8);
                if (letra == 'K' || letra == 'P' || letra == 'Q' || letra == 'S' || letra == 'W') {

                    //Must be a letter
                    return (controlChar == controlLetters[pos]);
                } else if (letra == 'A' || letra == 'B' || letra == 'E' || letra == 'H') {

                    return (Integer.parseInt("" + controlChar) == pos);
                } else {

                    return ((Character.isDigit(controlChar) && Integer.parseInt("" + controlChar) == pos) || (controlChar == controlLetters[pos]));
                }
            }
            //If first char is not a letter, we abort.
            return false;
        }
        //It must be of length 9. Abort.
        return false;
    }
}
