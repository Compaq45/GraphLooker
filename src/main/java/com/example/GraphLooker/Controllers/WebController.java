package com.example.GraphLooker.Controllers;

import com.example.GraphLooker.Util.GraphCreator;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {
    @GetMapping(path = "/")
    public HttpEntity<String> main(@RequestBody String body){
        var graph = GraphCreator.GraphFromString(body);
        var res = graph.GetShortestWay("A","C");
        var response = "Кратчайший путь: " + res.toString();
        return new HttpEntity<>(response);
    }
}
