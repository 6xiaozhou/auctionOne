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
public class GroupSigPrecompiledGroupSigVerifyInputBO {
  private String signature;

  private String message;

  private String gpkInfo;

  private String paramInfo;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(signature);
    args.add(message);
    args.add(gpkInfo);
    args.add(paramInfo);
    return args;
  }
}
