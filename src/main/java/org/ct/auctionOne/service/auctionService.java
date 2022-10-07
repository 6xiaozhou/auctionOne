package org.ct.auctionOne.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ct.auctionOne.constants.ContractConstants;
import org.ct.auctionOne.model.bo.auctionAuctionPublishInputBO;
import org.ct.auctionOne.model.bo.auctionAuctionResultPublicInputBO;
import org.ct.auctionOne.model.bo.auctionBiddingInputBO;
import org.ct.auctionOne.model.bo.auctionGetAuctionResultInputBO;
import org.ct.auctionOne.model.bo.auctionIsExitInputBO;
import org.ct.auctionOne.model.bo.auctionOpenBidInputBO;
import org.ct.auctionOne.model.bo.auctionToHexInputBO;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class auctionService {
  @Value("${contract.auctionAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse auctionResultPublic(auctionAuctionResultPublicInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "auctionResultPublic", input.toArgs());
  }

  public TransactionResponse getTime() throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "getTime", Arrays.asList());
  }

  public TransactionResponse openBid(auctionOpenBidInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "openBid", input.toArgs());
  }

  public TransactionResponse auctionPublish
          (auctionAuctionPublishInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "auctionPublish", input.toArgs());
  }

  public TransactionResponse bidding(auctionBiddingInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "bidding", input.toArgs());
  }

  public CallResponse toHex(auctionToHexInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.auctionAbi, "toHex", input.toArgs());
  }

  public TransactionResponse getAuctionResult(auctionGetAuctionResultInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "getAuctionResult", input.toArgs());
  }

  public TransactionResponse isExit(auctionIsExitInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.auctionAbi, "isExit", input.toArgs());
  }
}
