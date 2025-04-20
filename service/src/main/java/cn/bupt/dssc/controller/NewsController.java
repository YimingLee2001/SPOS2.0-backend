package cn.bupt.dssc.controller;

import cn.bupt.dssc.common.utils.BeanUtils;
import cn.bupt.dssc.common.utils.Convert;
import cn.bupt.dssc.domain.po.NewsEn;
import cn.bupt.dssc.domain.po.NewsZh;
import cn.bupt.dssc.domain.vo.NewsEnVO;
import cn.bupt.dssc.domain.vo.NewsZhVO;
import cn.bupt.dssc.service.INewsEnService;
import cn.bupt.dssc.service.INewsZhService;
import cn.bupt.dssc.utils.ConvertBase64;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Api(tags = "新闻管理接口")
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final INewsEnService newsEnService;
    private final INewsZhService newsZhService;

    private Convert<NewsEn, NewsEnVO> newsEn2VO = (NewsEn origin, NewsEnVO target) -> {
        // 处理日期
        Timestamp timestamp = origin.getTimestamp();
        LocalDateTime localDateTime = timestamp.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedData = localDateTime.format(formatter);
        target.setTimestamp(formattedData);

        // 处理图片
        String uri = "/root/SPOS2.0-backend/news_img/" + origin.getId() + ".jpg"; // 服务器上的地址
//        String uri = "C:/Users/Yimin/Pictures/" + "pig.jpg"; // 我的机器上的地址
        target.setImg(ConvertBase64.uri2Base64(uri));
    };

    private Convert<NewsZh, NewsZhVO> newsZh2VO = (NewsZh origin, NewsZhVO target) -> {
        // 处理日期
        Timestamp timestamp = origin.getTimestamp();
        LocalDateTime localDateTime = timestamp.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedData = localDateTime.format(formatter);
        target.setTimestamp(formattedData);

        // 处理图片
        String uri = "/root/SPOS2.0-backend/news_img/" + origin.getId() + ".jpg"; // 服务器上的地址
//        String uri = "C:/Users/Yimin/Pictures/" + "pig.jpg"; // 我的机器上的地址
        target.setImg(ConvertBase64.uri2Base64(uri));
    };

    @ApiOperation("根据id查询中文新闻")
    @GetMapping("/zh")
    public NewsZhVO getZhNewsById2(@RequestParam(required = true) int id) {
        NewsZh news = newsZhService.getById(id);
        return BeanUtils.copyBean(news, NewsZhVO.class, newsZh2VO);
    }

    @ApiOperation("根据id查询英文新闻")
    @GetMapping("/en")
    public NewsEnVO getEnNewsById2(@RequestParam(required = true) int id) {
        NewsEn news = newsEnService.getById(id);
        return BeanUtils.copyBean(news, NewsEnVO.class, newsEn2VO);
    }

    @ApiOperation("根据关键字查询中文新闻")
    @GetMapping("/keywordZh")
    public List<NewsZhVO> getZhNewsByKeyword(@RequestParam(required = true) String keyword) {
        LambdaQueryWrapper<NewsZh> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(NewsZh::getContent, keyword);
        List<NewsZh> newsList = newsZhService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(newsList, NewsZhVO.class, newsZh2VO);
    }

    @ApiOperation("根据关键字查询英文新闻")
    @GetMapping("/keywordEn")
    public List<NewsEnVO> getEnNewsByKeyword(@RequestParam(required = true) String keyword) {
        LambdaQueryWrapper<NewsEn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(NewsEn::getContent, keyword);
        List<NewsEn> newsList = newsEnService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(newsList, NewsEnVO.class, newsEn2VO);
    }

    @ApiOperation("查询最新中文新闻")
    @GetMapping("/headlinesZh")
    public List<NewsZhVO> getHeadLines(@RequestParam(defaultValue = "10") Integer num_limit, @RequestParam(defaultValue = "") String keyword) {
        LambdaQueryWrapper<NewsZh> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(NewsZh::getTimestamp)
                .like(NewsZh::getTitle, keyword)
                .isNotNull(NewsZh::getImg_url)
                .last("LIMIT " + num_limit);
        List<NewsZh> newsList = newsZhService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(newsList, NewsZhVO.class, newsZh2VO);
    }
}
