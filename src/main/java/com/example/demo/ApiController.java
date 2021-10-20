package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private List<String> messages = new ArrayList<>();
    @GetMapping("messages")
    public List<String> getMessages() {
        return messages;
    }
    /* curl -X POST http://localhost:8080/messages -H 'Content-Type: text/plain' -d 'text' */
    @PostMapping("messages")
    public void addMessage(@RequestBody String text) {
        messages.add(text);
    }
    //curl -X GET  http://localhost:8080/messages/count
    @GetMapping("messages/{index}")
    public String getMessage(@PathVariable("index") Integer index) {
        return messages.get(index);
    }
    @DeleteMapping("messages/{index}")
    public void deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
    }
    @PutMapping("messages/{index}")
    public void updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
    }
    @GetMapping("messages/count")
    public Integer getMessagescount() {
        int k = 0;
        for(int i = 0; i<messages.size(); i++) {
            k+=1;
            System.out.println(k);
        }
        return (k);
    }
    @PostMapping("messages/{index}")
    public void addMessagecreate(@PathVariable("index") Integer i,
                                  @RequestBody String message) {
        messages.add(i, message);
    }
    @GetMapping("messages/search/{text}")
    public int getMessagetext(@PathVariable("text") String b) {
        int k = 0;
        for(int i = 0; i<messages.size(); i++) {
            String a = messages.get(i);
            if (a.contains(b)) {
               k = i;
               break;
            }
        }
        return k;
    }
    @DeleteMapping("messages")
    public void deleteText() {
        for(int i = 0; i<messages.size(); i++) {
            String a = messages.get(i);
            if (a.contains("text")) {
                messages.remove(i);
            }
        }
    }

    //Get /messages/search/{text}, который возвращает индекс первого текста с подстрокой text
    //Get /messages/count, который возвращает количество сообщений
    //Post /messages/{index}/create, который добавляет сообщение с порядковым номером index
   // Delete /messages/search/{text} удаляет все сообщения в которых есть подстрока text
    //Примеры curl запросов написать над каждым методом
}