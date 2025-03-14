package cn.bupt.dssc.controller;

import cn.bupt.dssc.common.utils.BeanUtils;
import cn.bupt.dssc.common.utils.Convert;
import cn.bupt.dssc.domain.po.FijiCriticalEntity;
import cn.bupt.dssc.domain.vo.FijiCriticalEntityVO;
import cn.bupt.dssc.service.IFijiCriticalEntityService;
import cn.bupt.dssc.utils.ConvertBase64;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "名人实体管理接口")
@RestController
@RequestMapping("/entity")
@RequiredArgsConstructor
public class CriticalEntityController {
    private final IFijiCriticalEntityService criticalEntityService;
    private Convert<FijiCriticalEntity, FijiCriticalEntityVO> entity2vo = (FijiCriticalEntity origin, FijiCriticalEntityVO target) -> {
        String uri = "/root/SPOS2.0-backend/entity_img/" + origin.getEid() + ".png";
        target.setImage(ConvertBase64.uri2Base64(uri));
    };

    @ApiOperation(("根据姓名模糊查询名人实体"))
    @GetMapping("/celebrity/condition")
    public List<FijiCriticalEntityVO> getCelebrityByName(@RequestParam(required = true) String name) {
        LambdaQueryWrapper<FijiCriticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(FijiCriticalEntity::getName, name);
        List<FijiCriticalEntity> entityList = criticalEntityService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(entityList, FijiCriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据eid查询名人实体"))
    @GetMapping("/celebrity/id")
    public FijiCriticalEntityVO getCelebrityById(@RequestParam(required = true) String eid) {
        FijiCriticalEntity entity = criticalEntityService.getById(eid);
        return BeanUtils.copyBean(entity, FijiCriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据姓名模糊查询名人实体"))
    @GetMapping("/condition")
    public List<FijiCriticalEntityVO> getCelebrityByName2(@RequestParam(required = true) String name) {
        LambdaQueryWrapper<FijiCriticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(FijiCriticalEntity::getName, name);
        List<FijiCriticalEntity> entityList = criticalEntityService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(entityList, FijiCriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据eid查询名人实体"))
    @GetMapping("/{id}")
    public FijiCriticalEntityVO getCelebrityById2(@PathVariable String id) {
        FijiCriticalEntity entity = criticalEntityService.getById(id);
        return BeanUtils.copyBean(entity, FijiCriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据eid查询名人实体"))
    @GetMapping("/celebrity/{id}")
    public FijiCriticalEntityVO getCelebrityById3(@PathVariable String id) {
        FijiCriticalEntity entity = criticalEntityService.getById(id);
        return BeanUtils.copyBean(entity, FijiCriticalEntityVO.class, entity2vo);
    }
}
