package xin.nicholas.google.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.nicholas.google.GoogleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicholas on 17-9-18.
 */
@Controller

public class SimpleController{
    @Autowired
    private GoogleService googleService;

    @RequestMapping("/hello")
    @ResponseBody
    public List<Integer> getNumber(){
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        return res;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<String>getTiles(@RequestParam String key, @RequestParam(required = false) Integer limit){
       return googleService.getTiles(key,20);
    }

}
