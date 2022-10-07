package org.ct.auctionOne.model.bo;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class auctionIsExitInputBO {
  private byte[] b;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(b);
    return args;
  }
}
