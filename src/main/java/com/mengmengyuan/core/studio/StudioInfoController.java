package com.mengmengyuan.core.studio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;

import net.sf.json.JSONArray;

@RestController
@RequestMapping(path = "studio", method = { RequestMethod.POST })
public class StudioInfoController extends BaseController {

    @RequestMapping("uploadStudio")
    public ApiResponse uploadStudio(HttpServletRequest request, HttpServletResponse response) throws Exception { // 获取文件需要上传到的路径
        getFileFromRequest(request, response);
        return null;
    }

    public InputStream getFileFromRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取文件需要上传到的路径
        File directory = new File("..");
        String path = directory.getCanonicalPath() + "\\upload\\";

        // 判断存放上传文件的目录是否存在（不存在则创建）
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        logger.debug("path=" + path);

        request.setCharacterEncoding("utf-8"); // 设置编码

        JSONArray jsonArray = new JSONArray();

        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                HashMap<String, Object> res = new HashMap<String, Object>();
                MultipartFile file = req.getFile(iterator.next());
                // 获取文件名
                String fileNames = file.getOriginalFilename();
                int split = fileNames.lastIndexOf(".");
                // 获取上传文件的后缀
                String extName = fileNames.substring(split + 1, fileNames.length());
                // 申明UUID
                String uuid = UUID.randomUUID().toString().replace("-", "");

                // 组成新的图片名称
                String newName = uuid + "." + extName;
                System.out.println(newName);

                String destPath = path + newName;
                logger.info("destPath=" + destPath);

                // 真正写到磁盘上
                File file1 = new File(destPath);
                OutputStream out = new FileOutputStream(file1);
                out.write(file.getBytes());
                res.put("url", destPath);
                // result.setValue(jsonArray);
                jsonArray.add(res);

                out.close();
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        printWriter.write(jsonArray.toString());
        printWriter.flush();
        return null;

    }

}
