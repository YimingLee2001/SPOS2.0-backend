package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.domain.po.FijiNews;
import cn.bupt.dssc.mapper.FijiNewsMapper;
import cn.bupt.dssc.service.IFijiNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FijiNewsServiceImpl extends ServiceImpl<FijiNewsMapper, FijiNews> implements IFijiNewsService {
}
