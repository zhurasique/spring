package letscode.sarafan.controller;

import letscode.sarafan.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>(){{
       add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
       add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
       add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
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
    public Map<String, String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message){
        message.put("id", String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}
