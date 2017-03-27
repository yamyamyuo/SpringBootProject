package com.example.myFirstProject.controller;

import com.example.myFirstProject.domain.Coins;
import com.example.myFirstProject.service.ProcessAccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by serena on 3/27/17.
 */
@RestController
public class ProcessAccessController {
    @Resource(name = "processAccessImp")
    private ProcessAccess processAccess;

    @RequestMapping(value = "/ops/jstack", method = RequestMethod.GET)
    public String printProcess() {
       return processAccess.printProcess();
    }
}
