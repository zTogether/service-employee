package cn.xyzs.api.employee.controller.sys;

import cn.xyzs.api.employee.service.flow.TestService;
import cn.xyzs.api.employee.utils.UploadUtils;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class testController {

    @Resource
    private TestService testService;
    @GetMapping("index")
    public String index(){
        testService.add1("1233456");
        testService.flow1("215C802EACE048108F8E3128AB5A941C","6F45191BBB79479BA5FFFBCFDE8FE714",true);
        return "Hello";
    }

    @Value(value="${imgPath}")    //后台图片保存地址
    private String imgPath;

    @Value(value="${uploadHost}")
    private String uploadHost;    //项目host路径

    /**
     * 上传示例
     * @param request
     * @param response
     */
    @RequestMapping(value="uploadSysHeadImg.do", method= RequestMethod.POST)
    public void uploadSysHeadImg(HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest Murequest = resolver.resolveMultipart(request);
            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
            // 实例化一个jersey
            Client client = new Client();

            List<String> fileNameList = new ArrayList<>();
            List<String> relaPathList = new ArrayList<>();
            List<String> realPathList = new ArrayList<>();
            for(MultipartFile pic: files.values()){
                String uploadInfo = UploadUtils.upload(client, pic, request, response, uploadHost, imgPath);
                if(!"".equals(uploadInfo)){    //上传成功
                    String[] infoList = uploadInfo.split(";");
                    fileNameList.add(infoList[0]);    //文件名
                    relaPathList.add(infoList[1]);    //相对路径
                    realPathList.add(infoList[2]);    //真实完整路径
                }else{    //上传失败
                    fileNameList.add("");
                    relaPathList.add("");
                    realPathList.add("");
                }
            }
            jo.put("success", 1);
            jo.put("error", null);
            jo.put("fileNameList", fileNameList);
            jo.put("relaPathList", relaPathList);
            jo.put("realPathList", realPathList);
        }catch (Exception e) {
            jo.put("success", 0);
            jo.put("error", "上传失败");
        }
    }
}
