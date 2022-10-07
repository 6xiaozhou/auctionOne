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
public class auctionBiddingInputBO {
  private byte[] auctionID;

  private byte[] commit;

  private String gs;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(auctionID);
    args.add(commit);
    args.add(gs);
    return args;
  }
}
