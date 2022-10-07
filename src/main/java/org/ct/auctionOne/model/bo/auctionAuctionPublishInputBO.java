package org.ct.auctionOne.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class auctionAuctionPublishInputBO {
  private BigInteger sT;

  private BigInteger eT;

  private String gpk;

  private String pbc_param;

  private String goodI;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(sT);
    args.add(eT);
    args.add(gpk);
    args.add(pbc_param);
    args.add(goodI);
    return args;
  }
}
