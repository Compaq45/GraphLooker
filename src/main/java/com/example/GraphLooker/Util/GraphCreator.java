package com.example.GraphLooker.Util;

import com.example.GraphLooker.Model.UndirectedGraph;
import com.example.GraphLooker.Model.UndirectedGraphNode;


public class GraphCreator {
    public static UndirectedGraph GraphFromString(String body) {
        var bdy = body.toUpperCase();

        var vPos = bdy.indexOf("VERTEXES");
        if(vPos < 0 ) return null;

        var vStart = bdy.indexOf("{",vPos);
        var vEnd = bdy.indexOf("}",vPos);
        if (vStart > vEnd) return null;

        var rPos = bdy.indexOf("RIBS");
        if (rPos < 0) return null;

        var rStart = bdy.indexOf("{",rPos);
        var rEnd = bdy.indexOf("}",rPos);
        if (rStart > rEnd) return null;

        StringBuilder stringBuilder = new StringBuilder(body.substring(vStart + 1, vEnd -1));
        while (stringBuilder.indexOf("\n") >=0) stringBuilder.deleteCharAt(stringBuilder.indexOf("\n"));
        while (stringBuilder.indexOf("\r") >=0) stringBuilder.deleteCharAt(stringBuilder.indexOf("\r"));

        UndirectedGraph result = new UndirectedGraph();
        var sVertexes = stringBuilder.toString().split(";");
        for (var item:
             sVertexes) {
            if (item.trim().length()>0) result.AddVertex(new UndirectedGraphNode(item.trim()));
        }

        stringBuilder = new StringBuilder(body.substring(rStart + 1, rEnd -1));
        while (stringBuilder.indexOf("\n") >=0) stringBuilder.deleteCharAt(stringBuilder.indexOf("\n"));
        while (stringBuilder.indexOf("\r") >=0) stringBuilder.deleteCharAt(stringBuilder.indexOf("\r"));

        var sRibs = stringBuilder.toString().split(";");
        for (var item:
                sRibs) {
            if (item.trim().length()>0) {
                var tmp = item.split(":");
                var from = result.FindVertexByName(tmp[0].trim());
                var to = result.FindVertexByName(tmp[1].trim());
                Long weight = Long.parseLong(tmp[2].trim());
                result.AddRib(from,to,weight);
            }

        }
        return result;
    }

    public static String[] GetRoute(String body) {
        body = body.toUpperCase();
        var rPos = body.indexOf("ROUTE");
        if (rPos < 0) return null;
        var substr = body.substring(body.indexOf("{",rPos+1)+1,body.indexOf("}", rPos+1)).trim().split(";");
        if(substr.length != 2) return null;
        substr[0] = substr[0].trim();
        substr[1] = substr[1].trim();
        return substr;
    }
}
