package org.ct.auctionOne.constants;

import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.String;

public class ContractConstants {
  public static String auctionAbi;

  public static String auctionBinary;

  public static String auctionGmBinary;

  public static String GroupSigPrecompiledAbi;

  public static String GroupSigPrecompiledBinary;

  public static String GroupSigPrecompiledGmBinary;

  static {
    try {
      auctionAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/auction.abi"));
      auctionBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/auction.bin"));
      auctionGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/auction.bin"));
      GroupSigPrecompiledAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/GroupSigPrecompiled.abi"));
      GroupSigPrecompiledBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/GroupSigPrecompiled.bin"));
      GroupSigPrecompiledGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/GroupSigPrecompiled.bin"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
