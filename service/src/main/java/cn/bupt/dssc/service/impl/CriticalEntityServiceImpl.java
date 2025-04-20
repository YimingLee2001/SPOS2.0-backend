package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.domain.po.CriticalEntity;
import cn.bupt.dssc.mapper.CriticalEntityMapper;
import cn.bupt.dssc.service.ICriticalEntityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CriticalEntityServiceImpl extends ServiceImpl<CriticalEntityMapper, CriticalEntity> implements ICriticalEntityService {
}
