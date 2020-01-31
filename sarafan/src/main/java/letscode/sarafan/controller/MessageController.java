package letscode.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import letscode.sarafan.domain.Message;
import letscode.sarafan.domain.Views;
import letscode.sarafan.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("help")
    public String string(){
        return "// GET all\n" +
                "fetch('/message/').then(response => response.json().then(console.log))\n" +
                "\n" +
                "// GET one\n" +
                "fetch('/message/2').then(response => response.json().then(console.log))\n" +
                "\n" +
                "// POST add new one\n" +
                "fetch(\n" +
                "  '/message', \n" +
                "  { \n" +
                "    method: 'POST', \n" +
                "    headers: { 'Content-Type': 'application/json' },\n" +
                "    body: JSON.stringify({ text: 'Fourth message (4)', id: 10 })\n" +
                "  }\n" +
                ").then(result => result.json().then(console.log))\n" +
                "\n" +
                "// PUT save existing\n" +
                "fetch(\n" +
                "  '/message/4', \n" +
                "  { \n" +
                "    method: 'PUT', \n" +
                "    headers: { 'Content-Type': 'application/json' }, \n" +
                "    body: JSON.stringify({ text: 'Fourth message', id: 10 })\n" +
                "  }\n" +
                ").then(result => result.json().then(console.log));\n" +
                "\n" +
                "// DELETE existing\n" +
                "fetch('/message/4', { method: 'DELETE' }).then(result => console.log(result))";
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
       messageRepo.delete(message);
    }
}
