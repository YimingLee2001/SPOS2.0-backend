package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.SPOSApplication;
import cn.bupt.dssc.domain.po.FijiCriticalEntity;
import cn.bupt.dssc.service.IFijiCriticalEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SPOSApplication.class)
public class FijiCriticalEntityServiceImplTest {

    @Autowired
    private IFijiCriticalEntityService entityService;

    @Test
    public void getByIdTest() {
        if (entityService == null) {
            System.out.println("The service is null.");
            return;
        }
        String eid = "Q482355";
//        QueryWrapper<FijiCriticalEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("eid", eid);
        FijiCriticalEntity entity = entityService.getById(eid);
        if (entity == null) {
            System.out.println("it's null.");
        }
        System.out.println(entity.getName());
    }
}
