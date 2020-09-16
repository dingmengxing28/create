package cn.xkw.springautocreate.web;

import cn.xkw.springautocreate.domain.Model;
import cn.xkw.springautocreate.utils.DateUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: 徐康炜
 * @Date: 2020/9/8 19:48
 * @ProjectName: srbank
 * @Version 1.0
 * @Description: 历史记录控制器
 */
@RestController
public class HistoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/saveHistory")
    @ResponseBody
    public Boolean saveHistory(@RequestBody Model model) {
        Boolean aBoolean = true;
        try {
            String key = model.getClassName();
            // 日期
            String date = DateUtil.getCurrenceTimeToString("yyyy-MM-dd-HH-mm-sss");
            Gson gson = new Gson();
            String json = gson.toJson(model);
            redisTemplate.opsForValue().set("auto:" + key + ":" + date, json);
        } catch (Exception e) {
            LOGGER.info("---保存记录异常---", e);
            aBoolean = false;
        }
        return aBoolean;
    }

    @GetMapping("/listKeys")
    @ResponseBody
    public List<String> listKeys(@RequestParam(required = false) String key) {
        List<String> strings = new ArrayList<>();
        Set<String> keys = null;
        try {
            // 判断key是否输入
            if (StringUtils.isNotEmpty(key)) {
                // 匹配查询key
                keys = redisTemplate.keys("auto:" + key + "*");
                for (String k : keys) {
                    String[] split = k.split(":");
                    strings.add(split[1] + ":" + split[2]);
                }
            } else {
                // 查询出所有的key
                keys = redisTemplate.keys("auto:*");
                for (String k : keys) {
                    String[] split = k.split(":");
                    strings.add(split[1] + ":" + split[2]);
                }
            }
        } catch (Exception e) {
            LOGGER.info("---查询记录异常---", e);
        }
        return strings;
    }

    @GetMapping("/listHistory")
    @ResponseBody
    public String listHistory(String key) {
        String result = "";
        try {
            if (StringUtils.isNotEmpty(key)) {
                // 按key查询
                String value = redisTemplate.opsForValue().get("auto:" + key);
                if (StringUtils.isNotEmpty(value)) {
                    result = value;
                }
            }
        } catch (Exception e) {
            LOGGER.info("---查询记录异常---", e);
        }
        return result;
    }

    @GetMapping("/delHistory")
    @ResponseBody
    public String delHistory(String key) {
        String result = "success";
        try {
            if (StringUtils.isNotEmpty(key)) {
                redisTemplate.delete("auto:" + key);
            }
        } catch (Exception e) {
            LOGGER.info("---删除记录异常---", e);
            result = "";
        }
        return result;
    }
}
