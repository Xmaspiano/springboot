package hello;

import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.service.HrmresourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    HrmresourceService hrmresourceService;

    @RequestMapping("/SS")
    @ResponseBody
    List home() {
        List<Hrmresource> hrmresourceList = hrmresourceService.findByDDC();
        return hrmresourceList;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
//        oracle.jdbc.OracleDriver oracleDriver = new OracleDriver();
    }
}