package com.zj.util.test;

import com.zj.util.collection.CollectionsUtil;
import com.zj.util.collection.sort.Sort;
import com.zj.util.collection.sort.SortKey;
import com.zj.util.excel.ExcelUtil;
import com.zj.util.test.vo.User;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 集合工具测试 <br/>
 * @date 2017-08-23 下午 5:05 <br/>
 */
public class CollectionsUtilTest {
    @Test
    public void printSimpleCollection2Console() throws Exception {
        List<Map<String, Object>> result = ExcelUtil.getExcel2Maps("C:\\Users\\Administrator\\Desktop\\a.xlsx",0);
        CollectionsUtil.printSimpleCollection2Console(result);
    }

    @Test
    public void sortMaps() throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String,Object> map = new HashedMap();
            map.put("id",i);
            map.put("name","name"+i);
            map.put("age", new Random().nextInt(100));
            result.add(map);
        }
        List<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new SortKey("age", Sort.DESC));
        sortKeys.add(new SortKey("id", Sort.ASC));
        CollectionsUtil.sortMaps(result,sortKeys);
        CollectionsUtil.printSimpleCollection2Console(result);
    }

    @Test
    public void sortBeans() throws Exception {
        List<User> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name"+new Random().nextInt(100));
            result.add(user);
        }
        List<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new SortKey("name", Sort.DESC));
        sortKeys.add(new SortKey("id", Sort.ASC));
        CollectionsUtil.sortBeans(result,sortKeys);
        CollectionsUtil.printSimpleCollection2Console(result);
    }

    @Test
    public void isNotEmpty() throws Exception {
        List<Integer> list = new ArrayList<>();
        System.out.println(CollectionsUtil.isNotEmpty(list));
        list.add(1);
        System.out.println(CollectionsUtil.isNotEmpty(list));
    }
}
