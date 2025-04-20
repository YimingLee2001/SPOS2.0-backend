package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.domain.po.NewsZh;
import cn.bupt.dssc.mapper.NewsZhMapper;
import cn.bupt.dssc.service.INewsZhService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NewsZhServiceImpl extends ServiceImpl<NewsZhMapper, NewsZh> implements INewsZhService {
}
