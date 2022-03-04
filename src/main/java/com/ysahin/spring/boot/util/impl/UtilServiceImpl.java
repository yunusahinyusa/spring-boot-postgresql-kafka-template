package com.ysahin.spring.boot.util.impl;

import com.ysahin.spring.boot.service.KafkaService;
import com.ysahin.spring.boot.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class UtilServiceImpl implements UtilService {
    private String fileHandler = "request_param.log";
    private Logger logger = Logger.getLogger("MyLog");
    private FileHandler fh;
    private String sCurrentLine;
    private String lastLine;

    @Autowired
    KafkaService kafkaService;

    @Override
    public void createLogFile(HttpServletRequest request, Long time) throws IOException {
        fh = new FileHandler(fileHandler);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.info( request.getMethod() + "," + time + "," + System.currentTimeMillis());
    }

    @Override
    public void readLastLine() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileHandler));
        while ((sCurrentLine = br.readLine()) != null)
        {
            lastLine = sCurrentLine;
        }
        kafkaService.sendKafkaMessageProducer(lastLine);
    }
}
