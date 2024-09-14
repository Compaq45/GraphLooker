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
        var route = GraphCreator.GetRoute(body);
        var res = (route != null && graph != null)? graph.GetShortestWay(route[0],route[1]): null;
        String response = "Кратчайший путь: " + (res!=null? res.toString(): "Невозможно определить");
        return new HttpEntity<>(response);
    }
}
