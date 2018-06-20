package com.mengmengyuan.core.studio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;

//
//@RestController
//@RequestMapping(path = "studio", method = { RequestMethod.POST })
public class StudioInfoController extends BaseController {

    // @RequestMapping("uploadStudio")
    public ApiResponse uploadStudio(HttpServletRequest request, HttpServletResponse response) throws Exception { // 获取文件需要上传到的路径
        File file = getFileFromRequest(request);
        if (file == null) {

        }

        return null;
    }

    public File getFileFromRequest(HttpServletRequest request) throws IOException {
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

        File file = null;
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile files = req.getFile(iterator.next());
                // 获取文件名
                String fileNames = files.getOriginalFilename();
                int split = fileNames.lastIndexOf(".");
                // 获取上传文件的后缀
                String extName = fileNames.substring(split + 1, fileNames.length());
                // 申明UUID
                String uuid = UUID.randomUUID().toString().replace("-", "");

                // 组成新的名称
                String newName = uuid + "." + extName;
                logger.info("file" + newName);
                String destPath = path + newName;
                logger.info("destPath=" + destPath);
                // 真正写到磁盘上
                file = new File(destPath);
                OutputStream out = new FileOutputStream(file);
                out.write(files.getBytes());
                out.close();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return file;

    }

}
