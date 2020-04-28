package com.xlx.common.util;

import com.sun.mail.smtp.DigestMD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author xielx at 2020/4/28 16:49
 */
@Slf4j
public class FileUtil {
    
    
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 52428800;
    
    /**
     * 默认上传的地址
     */
    private static final String defaultBaseDir = "D://download";
    
    /**
     * 默认的文件名最大长度
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;
    
    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";
    
    private static int counter = 0;
    
    
    public static String upload(String baseDir, MultipartFile file) throws  IOException {
        try{
            return upload(baseDir,file,IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
    public static String upload(String baseDir, MultipartFile file,String extension) throws FileUploadBase.FileSizeLimitExceededException, IOException {
        
        // 校验文件
        validateFile(file);
        // 文件名编码
        String name  = encodeFileName(Objects.requireNonNull(file.getOriginalFilename()),extension);
        // File对象
        File file1 = getAbsolutePath(baseDir,baseDir+name);
        // 上传
        file.transferTo(file1);
        
        return name;
    }
    
    private static File getAbsolutePath(String upLoadDir, String filename) throws IOException {
        File file = new File(File.separator + filename);
        // 目录
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        
        // 文件
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }
    
    private static String encodeFileName(String fileName, String extension) {
        String replace = fileName.replace("_", " ");
    
        String md5Hex = DigestUtils.md5Hex(replace);
        return new String(md5Hex.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8) + extension;
    }
    
    private static void validateFile(MultipartFile file) throws FileUploadBase.FileSizeLimitExceededException {
        int name_length = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (name_length > FileUtil.DEFAULT_FILE_NAME_LENGTH){
            throw new FileUploadBase.FileSizeLimitExceededException(file.getOriginalFilename(),name_length , FileUtil.DEFAULT_FILE_NAME_LENGTH);
        }
    
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE){
            throw new FileUploadBase.FileSizeLimitExceededException("not allowed upload upload", size, DEFAULT_MAX_SIZE);
        }
    }
}
