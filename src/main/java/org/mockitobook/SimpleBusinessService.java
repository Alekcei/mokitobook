package org.mockitobook;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SimpleBusinessService {

    public List resVal = new LinkedList();
    public void callFunc(String  inParam) {
        resVal.add(inParam);
    }

    public String runFuncWithParams(String  inParam) {
        resVal.add(inParam);
        return "isOriginalResponse";
    }

    public String getConfigure(String typeRequest) {
        resVal.add(typeRequest);
        return "is configure for " + typeRequest;
    }
}
