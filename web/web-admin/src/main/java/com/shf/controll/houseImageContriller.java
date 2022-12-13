package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.HouseImage;
import com.shf.result.Result;
import com.shf.service.HouseImageService;
import org.aspectj.apache.bcel.generic.MULTIANEWARRAY;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.shf.util.QiniuUtils.deleteFileFromQiniu;
import static com.shf.util.QiniuUtils.upload2Qiniu;

@RequestMapping("houseImage")
@Controller
public class houseImageContriller {
    @Reference
    HouseImageService houseImageService;
    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String updateShow(@PathVariable("houseId")Long houseId, @PathVariable("type")Integer type, Map map){
        map.put("houseId",houseId);
        map.put("type",type);
        return "house/upload";
    }
    @ResponseBody
    @RequestMapping("/upload/{houseId}/{type}")
    public Result updateShow(@PathVariable("houseId")Long houseId, @PathVariable("type")Integer type, @RequestParam("file")MultipartFile[] files){
//         两个目标第一个把图片传到七牛云上,第二个把图片传到数据库上然后就是把图片变成二进制好进行传输,
        try{
            if(files!=null &&files.length>0){
                for(MultipartFile file:files){
                    //遍历数据得到单个数据,然后进行二进制的传输
                    byte[] bytes = file.getBytes();
                    String originalFilename = file.getOriginalFilename();
//为了保持名字不一致的方法,采取了一个随机生成字节码的方法
                    String newfilename = UUID.randomUUID().toString();

                    upload2Qiniu(bytes,newfilename);
                    //增加到数据库之中
                    HouseImage houseImage = new HouseImage();
                    houseImage.setHouseId(houseId);
                    houseImage.setType(type);
                    houseImage.setImageName(originalFilename);
                    houseImage.setImageUrl("http://rm56bq9kk.hn-bkt.clouddn.com"+newfilename);

                    houseImageService.insert(houseImage);
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
   return Result.ok();
    }
    @RequestMapping("/delete/{houseId}/{houseImageId}")
    public String delete(@PathVariable("houseId")Long houseId,@PathVariable("houseImageId")Long houseImageId){
        //数据库删除,七牛云删除


        HouseImage byId = houseImageService.getById(houseImageId);
        String imageName = byId.getImageName();

        deleteFileFromQiniu(imageName);
        houseImageService.delete(houseImageId);
        return "redirect:/house/"+houseId;


    }

}
