package org.ct.auctionOne.utils;

import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;

import java.util.Collections;
import javax.xml.bind.DatatypeConverter;

public class stringUtils {
    public static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }
    //
    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
    public static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString() + "".join("",
                Collections.nCopies(32 - (hex.length()/2), "00"));
    }
    public static String hexToASCII(String hexValue)
    {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexValue.length(); i += 2)
        {
            String str = hexValue.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

}
