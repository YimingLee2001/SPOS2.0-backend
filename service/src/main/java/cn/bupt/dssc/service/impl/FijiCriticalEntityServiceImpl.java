package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.domain.po.FijiCriticalEntity;
import cn.bupt.dssc.mapper.FijiCriticalEntityMapper;
import cn.bupt.dssc.service.IFijiCriticalEntityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FijiCriticalEntityServiceImpl extends ServiceImpl<FijiCriticalEntityMapper, FijiCriticalEntity> implements IFijiCriticalEntityService {
}
