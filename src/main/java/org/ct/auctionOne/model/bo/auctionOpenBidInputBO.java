package org.ct.auctionOne.model.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class auctionOpenBidInputBO {
  private byte[] bidID;

  private String price;

  private String random;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(bidID);
    args.add(price);
    args.add(random);
    return args;
  }
}
