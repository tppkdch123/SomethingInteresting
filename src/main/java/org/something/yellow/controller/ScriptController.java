package org.something.yellow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huangshizhe on 2018/9/9
 */

@Controller
@RequestMapping("/api/script/")
@Slf4j
public class ScriptController extends BaseController {

    private static final String LOG_PREFIX = "[脚本下载] ";

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadScript(HttpServletRequest request) throws IOException {
        //log.info("{} 下载人ip:{} ", LOG_PREFIX, request.get);
        File file = new File("/data/20180909.rar");
        byte[] result = null;
        InputStream inputStream = new FileInputStream(file);
        result = new byte[inputStream.available()];
        inputStream.read(result);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attchement;filename=" + file.getName());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(result, httpHeaders, HttpStatus.OK);
        return responseEntity;

    }
}
