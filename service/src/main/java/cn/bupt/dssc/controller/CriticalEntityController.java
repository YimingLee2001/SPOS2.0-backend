package cn.bupt.dssc.controller;

import cn.bupt.dssc.common.utils.BeanUtils;
import cn.bupt.dssc.common.utils.Convert;
import cn.bupt.dssc.domain.po.CriticalEntity;
import cn.bupt.dssc.domain.vo.CriticalEntityVO;
import cn.bupt.dssc.service.ICriticalEntityService;
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
    private final ICriticalEntityService criticalEntityService;
    private Convert<CriticalEntity, CriticalEntityVO> entity2vo = (CriticalEntity origin, CriticalEntityVO target) -> {
        String uri = "/root/SPOS2.0-backend/entity_img/" + origin.getEid() + ".png";
        target.setImage(ConvertBase64.uri2Base64(uri));
    };

    @ApiOperation(("根据姓名模糊查询名人实体"))
    @GetMapping("/celebrity/condition")
    public List<CriticalEntityVO> getCelebrityByName(@RequestParam(required = true) String name) {
        LambdaQueryWrapper<CriticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CriticalEntity::getName, name);
        List<CriticalEntity> entityList = criticalEntityService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(entityList, CriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据eid查询名人实体"))
    @GetMapping("/celebrity/id")
    public CriticalEntityVO getCelebrityById(@RequestParam(required = true) String eid) {
        CriticalEntity entity = criticalEntityService.getById(eid);
        return BeanUtils.copyBean(entity, CriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据姓名模糊查询名人实体"))
    @GetMapping("/condition")
    public List<CriticalEntityVO> getCelebrityByName2(@RequestParam(required = true) String name) {
        LambdaQueryWrapper<CriticalEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CriticalEntity::getName, name);
        List<CriticalEntity> entityList = criticalEntityService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(entityList, CriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据eid查询名人实体"))
    @GetMapping("/{id}")
    public CriticalEntityVO getCelebrityById2(@PathVariable String id) {
        CriticalEntity entity = criticalEntityService.getById(id);
        return BeanUtils.copyBean(entity, CriticalEntityVO.class, entity2vo);
    }

    @ApiOperation(("根据eid查询名人实体"))
    @GetMapping("/celebrity/{id}")
    public CriticalEntityVO getCelebrityById3(@PathVariable String id) {
        CriticalEntity entity = criticalEntityService.getById(id);
        return BeanUtils.copyBean(entity, CriticalEntityVO.class, entity2vo);
    }
}
