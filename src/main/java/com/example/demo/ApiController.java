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
    //Добавлять сообщение в список
    /* curl -X POST http://localhost:8080/messages -H 'Content-Type: text/plain' -d 'text' */
    @PostMapping("messages")
    public void addMessage(@RequestBody String text) {
        messages.add(text);
    }
    //Выводить на экран сообщение по индексу
    //curl -X GET  http://localhost:8080/messagess/{index}
    @GetMapping("messages/{index}")
    public String getMessage(@PathVariable("index") Integer index) {
        return messages.get(index);
    }
    //Удалять сообщение по индексу
    // curl -X DELETE http://localhost:8080/messages/{index} -H 'Content-Type: text/plain' -d 'text' 
    @DeleteMapping("messages/{index}")
    public void deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
    }
    //Обновлять сообщение по индексу
    // curl -X PUT http://localhost:8080/messages/{index} -H 'Content-Type: text/plain' -d 'text' 
    @PutMapping("messages/{index}")
    public void updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
    }
    // Get /messages/count, который возвращает количество сообщений
    // curl -X GET  http://localhost:8080/messagess/count
    @GetMapping("messages/count")
    public Integer getMessagescount() {
        int k = messages.size();
        return (k);
    }
    // Post /messages/{index}/create, который добавляет сообщение с порядковым номером index
    // /* curl -X POST http://localhost:8080/messages/{index} -H 'Content-Type: text/plain' -d 'text' */
    @PostMapping("messages/{index}")
    public void addMessagecreate(@PathVariable("index") Integer i,
                                  @RequestBody String message) {
        messages.add(i, message);
    }
    // Get /messages/search/{text}, который возвращает индекс первого текста с подстрокой text
    //curl -X GET  http://localhost:8080/messagess/search/{text}
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
    // Delete /messages/search/{text} удаляет все сообщения в которых есть подстрока text
    // curl -X DELETE http://localhost:8080/messages -H 'Content-Type: text/plain' -d 'text' 
    @DeleteMapping("messages")
    public void deleteText() {
        for(int i = 0; i<messages.size(); i++) {
            String a = messages.get(i);
            if (a.contains("text")) {
                messages.remove(i);
            }
        }
    }
}
