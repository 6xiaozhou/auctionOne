package org.ct.auctionOne.config;

import java.lang.String;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(
    prefix = "contract"
)
public class ContractConfig {
  private String auctionAddress;

  private String groupSigPrecompiledAddress;
}
