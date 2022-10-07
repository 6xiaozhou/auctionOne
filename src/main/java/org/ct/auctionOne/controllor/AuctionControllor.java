package org.ct.auctionOne.controllor;


import org.ct.auctionOne.model.bo.*;
import org.ct.auctionOne.service.auctionService;
import org.ct.auctionOne.utils.stringUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;



import static org.ct.auctionOne.utils.stringUtils.toByteArray;


/**
 * getTransactionReceipt().getTransactionHash() huodejiaoyi   hash
 */
@Controller
//@RequestMapping("auction")
public class AuctionControllor {
    @Autowired
    private auctionService service;


    @GetMapping("get")
    public List<Object> get() throws Exception{
        byte[] bytes = toByteArray("4b08a60c518a861f020a355d5dd9caa0c447af99c4844fe7da9eb8b219dbcc27");
        //byte[] b = new byte[]{Byte.parseByte("0x4b08a60c518a861f020a355d5dd9caa0c447af99c4844fe7da9eb8b219dbcc27",16)};
        //Bytes32 bytes32 = new Bytes32(s.getBytes());
        //byte[] myStringInByte = Numeric.hexStringToByteArray(asciiToHex(s));
        auctionGetAuctionResultInputBO auctionGetAuctionResultInput = new auctionGetAuctionResultInputBO(bytes);
        //auctionGetAuctionResultInput.setId(s.getBytes());
        ;
        // service.getAuctionResult(auctionGetAuctionResultInput);
//        String s = service.getAuctionResult(auctionGetAuctionResultInput).getValues();
//        s = s.substring(1,s.length()-1);
//        String[] strings = s.split("\"");
//        //String ss = stringUtils.hexToASCII(strings[1].getBytes(StandardCharsets.UTF_8).toString());
//
//        //System.out.println(strings[1].getBytes(StandardCharsets.UTF_8));
//        String ss = new String(strings[1].getBytes(StandardCharsets.UTF_8));
//        System.out.println(ss);
//        System.out.println(s);
        List<Object> list = service.getAuctionResult(auctionGetAuctionResultInput).getValuesList();
//        for (Object b:
//             list) {
//            System.out.println(b.getClass());
//        }
        System.out.println(list.get(0).toString());
        return list;
    }
    //publishAuctionResult
    @ResponseBody
    @RequestMapping("/publishAuctionResult")
    public Map<String,Object> publishAuctionResult(String maxPrice, String random, String address, String bidID, String proof) throws Exception {
        Map<String, Object> map = new HashMap<>();

        if (maxPrice.equals("")){
            map.put("error","paramError");
            return map;
        }
//        System.out.println(maxPrice);
//        System.out.println(random);
//        System.out.println(address);
//        System.out.println(bidID);
//        System.out.println(proof);
        auctionAuctionResultPublicInputBO auctionAuctionResultPublicInputBO = new auctionAuctionResultPublicInputBO();
        auctionAuctionResultPublicInputBO.setAdd(address);
        auctionAuctionResultPublicInputBO.setBID(toByteArray(bidID));
        auctionAuctionResultPublicInputBO.setPrice(maxPrice);
        auctionAuctionResultPublicInputBO.setProf(proof);
        auctionAuctionResultPublicInputBO.setRandom(random);
        TransactionResponse transactionResponse = service.auctionResultPublic(auctionAuctionResultPublicInputBO);
        map.put("transactionHash",transactionResponse.getTransactionReceipt().getTransactionHash());
        map.put("result",transactionResponse.getValues());
        return map;
    }
    //bidding
    @ResponseBody
    @GetMapping("/bidding")
    public Map<String, Object> bidding(String auctionID, String priceCommit, String groupSig) throws Exception {
//        System.out.println(auctionID);
//        System.out.println(priceCommit);
//        System.out.println(groupSig);
        Map<String , Object> map = new HashMap<>();
        if (auctionID.equals("")){
            map.put("error","paramError");
            return map;
        }
        auctionBiddingInputBO auctionBiddingInputBO = new auctionBiddingInputBO();
        byte[] bytes = toByteArray(auctionID);
        auctionBiddingInputBO.setAuctionID(bytes);
        auctionBiddingInputBO.setCommit(toByteArray(priceCommit));
        auctionBiddingInputBO.setGs(groupSig);
        TransactionResponse transactionResponse = service.bidding(auctionBiddingInputBO);
        String  transactionHash= transactionResponse.getTransactionReceipt().getTransactionHash();
        //daxie  zhuanhuanweixiaoxie
        String result = transactionResponse.getValues().toLowerCase(Locale.ROOT);

        map.put("transactionHash",transactionHash);
        map.put("bidResult",result);
        return map;
    }
    //publishAuctionInfo
    @GetMapping("/publish")
    @ResponseBody
    public Map<String,Object> publish(String start,String end,String pubParam,String group,String goodInfo) throws Exception {
        Map<String, Object> map =  new HashMap<>();
        if (start.equals("")){
            map.put("error","paramError");
            return map;
        }
        auctionAuctionPublishInputBO auctionAuctionPublishInput = new auctionAuctionPublishInputBO();
        auctionAuctionPublishInput.setST(BigInteger.valueOf(Long.parseLong(start)));
        auctionAuctionPublishInput.setET(BigInteger.valueOf(Long.parseLong(end)));
        auctionAuctionPublishInput.setGoodI(goodInfo);
        auctionAuctionPublishInput.setGpk(group);
        auctionAuctionPublishInput.setPbc_param(pubParam);
        TransactionResponse  transactionResponse = service.auctionPublish(auctionAuctionPublishInput);
        String auctionID = transactionResponse.getValues();
        String transactionHash = transactionResponse.getTransactionReceipt().getTransactionHash();
        auctionID = auctionID.toLowerCase(Locale.ROOT);

        map.put("transactionHash",transactionHash);
        map.put("auctionID",auctionID);
        return map;
    }

    @GetMapping("/openBid")
    @ResponseBody
    public Map<String,Object> openBid(String bidID,String price , String random) throws Exception {
        System.out.println(bidID);
        System.out.println(price);
        System.out.println(random);
        Map<String,Object> map = new HashMap<>();
        if (bidID.equals("")){
            map.put("error","paramError");
            return map;
        }
        auctionOpenBidInputBO auctionOpenBidInputBO = new auctionOpenBidInputBO();
        auctionOpenBidInputBO.setBidID(toByteArray(bidID));
        auctionOpenBidInputBO.setPrice(price);
        auctionOpenBidInputBO.setRandom(random);
        TransactionResponse transactionResponse = service.openBid(auctionOpenBidInputBO);
        map.put("transactionHash",transactionResponse.getTransactionReceipt().getTransactionHash());
        map.put("result",transactionResponse.getValues());
        return map;
    }
    @GetMapping("/getResult")
    @ResponseBody
    public Map<String,Object> getResult(String auctionID) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if (auctionID.equals("")){
            map.put("error","paramError");
        }
        //System.out.println(auctionID);
        auctionGetAuctionResultInputBO auctionGetAuctionResultInputBO = new auctionGetAuctionResultInputBO();
        auctionGetAuctionResultInputBO.setId(toByteArray(auctionID));
        TransactionResponse transactionResponse = service.getAuctionResult(auctionGetAuctionResultInputBO);
        map.put("transactionHash",transactionResponse.getTransactionReceipt().getTransactionHash());
        List<Object> list = transactionResponse.getValuesList();
        map.put("bidID",list.get(0));
        map.put("maxPrice",list.get(1));
        map.put("random",list.get(2));
        map.put("address",list.get(3));
        return map;
    }
    @GetMapping("/getTime")
    @ResponseBody
    public Date getTime(String start,String group) throws Exception {
        String s = service.getTime().getValues();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //int time = Integer.parseInt(s.substring(1,s.length()-1));
        System.out.println(group);
        long t = Long.parseLong(s.substring(1,s.length()-1))+Long.parseLong(start);
        Date data = new Date(t);
        //System.out.println(start);
        return data;
    }
    //@ResponseBody
    @RequestMapping("/publishAuction")
    public String publishAuction() {
        //ModelAndView modelAndView = new ModelAndView("publishAuction");
        //modelAndView.setViewName("publishAuction");
        //System.out.println();
        return "publishAuction.html";
    }

    @RequestMapping("/bidIndex")
    public String bidIndex() {
        return "biddingIndex.html";
    }

    @RequestMapping("/open")
    public String open() {
        return "openBidIndex.html";
    }
    @RequestMapping("/publishResult")
    public String publishResult() {
        return "auctionResultIndex.html";
    }
    @RequestMapping("/getAuctionResult")
    public String getAuctionResult() {
        return "getAuctionResult.html";
    }
}
