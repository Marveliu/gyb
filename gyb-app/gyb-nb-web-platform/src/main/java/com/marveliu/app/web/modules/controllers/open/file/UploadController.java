package com.marveliu.app.web.modules.controllers.open.file;


import com.marveliu.app.web.commons.base.Globals;
import com.marveliu.app.web.commons.utils.DateUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.nutz.img.Images;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * Created by Wizzer on 2016/7/5.
 */
@IocBean
@At("/open/file/upload")
public class UploadController {
    private static final Log log = Logs.get();


    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:imageUpload"})
    @POST
    @At
    @Ok("json")
    @RequiresAuthentication
    //AdaptorErrorContext必须是最后一个参数
    public Object image(@Param("Filedata") TempFile tf, HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return NutMap.NEW().addv("code", 1).addv("msg", "文件不合法");
            } else if (tf == null) {
                return Result.error("空文件");
            } else {
                String p = Globals.AppRoot;
                String f = Globals.AppUploadPath + "/image/" + DateUtil.format(new Date(), "yyyyMMdd") + "/" + R.UU32() + tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                Files.write(new File(p + f), tf.getInputStream());
                return Result.success("上传成功", Globals.AppBase+f);
            }
        } catch (Exception e) {
            return Result.error("系统错误");
        } catch (Throwable e) {
            return Result.error("图片格式错误");
        }
    }



    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:imageUpload"})
    @POST
    @At
    @Ok("json")
    public Object gyauimage(@Param("Filedata") TempFile tf, HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return NutMap.NEW().addv("code", 1).addv("msg", "文件不合法");
            } else if (tf == null) {
                return Result.error("空文件");
            } else {
                String p = Globals.AppRoot;
                String test = tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                log.debug(test);
                String f = Globals.AppUploadPath + "/image/gy/"+ StringUtil.getPlatformUid() + DateUtil.format(new Date(), "yyyyMMdd") + "/" + R.UU32() + tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                Files.write(new File(p + f), tf.getInputStream());
                return Result.success("上传成功", Globals.AppBase+f);
            }
        } catch (Exception e) {
            return Result.error("系统错误");
        } catch (Throwable e) {
            return Result.error("图片格式错误");
        }
    }

    /**
     * 上传图片缩放处理
     * @param tf
     * @param type      上传图片分类
     * @param resize    是否缩放
     * @param width     缩放宽度
     * @param height    缩放高度
     * @param req
     * @param err
     * @return
     */
    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:imageUpload"})
    @POST
    @At
    @Ok("json")
    @RequiresAuthentication
    public Object imageWithType(
            @Param("Filedata") TempFile tf,
            @Param("type") String type,
            @Param("resize") boolean resize,
            @Param("width") int width,
            @Param("height") int height,
            HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return NutMap.NEW().addv("code", 1).addv("msg", "文件不合法");
            } else if (tf == null) {
                return Result.error("空文件");
            } else {
                // 图片上传路径
                String filePath = "/image/"+type+"/" + DateUtil.format(new Date(), "yyyyMMdd") + "/";
                // 图片名称
                String fileName = R.UU32()+".jpg";
                // 图片文件地址
                String fileurl = Globals.AppRoot+Globals.AppUploadPath+filePath + fileName;
                File fp = new File(Globals.AppRoot+Globals.AppUploadPath+filePath);


                if (!fp.exists()) {
                    fp.mkdirs();
                }
                BufferedImage img = Images.read(tf.getFile());

                Thumbnails.Builder tb = Thumbnails.of(img);
                if(img.getWidth() > 1080){
                    tb.size(1960,1080);
                }else {
                    tb.scale(1.0);
                };
                if(resize) tb.size(width,height);
                tb.outputFormat("jpg");
                tb.outputQuality(0.8);
                tb.toFile(fileurl);
                // if(resize){
                //     Thumbnails.of(img).size(width,height)
                //             .outputFormat("jpg")
                //             .outputQuality(0.8)
                //             .toFile(fileurl);
                // }else{
                //     Thumbnails.of(img).scale(1.0)
                //             .outputFormat("jpg")
                //             .outputQuality(0.3)
                //             .toFile(fileurl);
                // }
                return Result.success("上传成功", filePath+fileName);
            }
        } catch (Exception e) {
            log.error("图片上传错误",e);
        }
        return Result.error("系统错误");
    }



    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:fileUpload"})
    @POST
    @At
    @Ok("json")
    @RequiresAuthentication
    //AdaptorErrorContext必须是最后一个参数
    public Object file(@Param("Filedata") TempFile tf, HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return NutMap.NEW().addv("code", 1).addv("msg", "文件不合法");
            } else if (tf == null) {
                return Result.error("空文件");
            } else {
                String s = tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf(".") + 1);
                String uri = "/file/" + DateUtil.format(new Date(), "yyyyMMdd") + "/" + R.UU32() + tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                String f = Globals.AppUploadPath + uri;
                Files.write(new File(f), tf.getInputStream());
                // return Result.success("上传成功", NutMap.NEW().addv("file_type", s.toLowerCase()).addv("file_name", tf.getName()).addv("file_size", tf.getSize()).addv("file_url", uri));
                return Result.success("上传成功", uri);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("系统错误");
        } catch (Throwable e) {
            return Result.error("文件格式错误");
        }
    }

}
