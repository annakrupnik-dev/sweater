package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repository.MessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Map<String,Object> model) {
         return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String,Object> model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                        @RequestParam String text,
                        @RequestParam String tag,
                        Map<String,Object> model)
    {
        Message message = new Message(text,tag,user);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("filter")
    public String findByTag(@RequestParam String tag,
                      Map<String,Object> model)
    {
        Iterable<Message> messages;
        if (StringUtils.isEmpty(tag)) {
            messages = messageRepository.findAll();
        } else {
            messages = messageRepository.findByTag(tag);
        }
        model.put("messages", messages);
        return "main";
    }
}
