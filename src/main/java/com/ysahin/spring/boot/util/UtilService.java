package com.ysahin.spring.boot.util;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public interface UtilService {
    public void createLogFile(HttpServletRequest request, Long time) throws IOException;
    public void readLastLine() throws IOException;
}
