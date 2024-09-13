package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.service.ClovaChatBotService;

@RestController
@RequestMapping("/api/clova")
public class ClovaChatBotController {

  @Autowired
  private ClovaChatBotService clovaChatBotService;

  @RequestMapping("/chatbotSend")
  public String chatbotSend(@RequestParam("inputText") String inputText) {
    String msg = "";
    msg = clovaChatBotService.main(inputText);
    System.out.println(msg);
    return msg;
  }
}