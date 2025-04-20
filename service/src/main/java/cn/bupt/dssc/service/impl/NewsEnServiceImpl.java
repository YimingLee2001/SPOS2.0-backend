package cn.bupt.dssc.service.impl;

import cn.bupt.dssc.domain.po.NewsEn;
import cn.bupt.dssc.mapper.NewsEnMapper;
import cn.bupt.dssc.service.INewsEnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NewsEnServiceImpl extends ServiceImpl<NewsEnMapper, NewsEn> implements INewsEnService {
}
