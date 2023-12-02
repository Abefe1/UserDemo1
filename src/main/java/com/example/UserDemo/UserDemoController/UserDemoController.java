package com.example.UserDemo.UserDemoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping("/messages")
public class UserDemoController {


    File file = new File("message.txt");
    @GetMapping("/getMessage")
    public String getMessages(){
        StringBuilder sb = new StringBuilder();
        String x;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            while((x = bufferedReader.readLine()) != null){
                sb.append(x);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return sb.toString();
    }

    @PostMapping("/postMessages")
    public String postMessages(@RequestBody String message){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))){
            bufferedWriter.write(message + "***");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return "message successfully posted";
    }

    @GetMapping("/getMessage")
    public int countMessages(){
        int messageCount=0;
        List<String> messageList= new ArrayList<>();
        String check;
        String myCheck=null;
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader counter = new BufferedReader(new FileReader(file))){
            while((check = counter.readLine()) != null){
                stringBuilder.append(check);
                myCheck=stringBuilder.toString();
            }

            messageList= Arrays.asList(myCheck.split("#"));
            messageCount=messageList.size()-1;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return messageCount;
    }


}
