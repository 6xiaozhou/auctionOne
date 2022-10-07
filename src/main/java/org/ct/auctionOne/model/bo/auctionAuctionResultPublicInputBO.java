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
public class auctionAuctionResultPublicInputBO {
  private String price;

  private String random;

  private byte[] bID;

  private String add;

  private String prof;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(price);
    args.add(random);
    args.add(bID);
    args.add(add);
    args.add(prof);
    return args;
  }
}
