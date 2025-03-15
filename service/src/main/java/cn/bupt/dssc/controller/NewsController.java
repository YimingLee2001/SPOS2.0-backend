package cn.bupt.dssc.controller;

import cn.bupt.dssc.common.utils.BeanUtils;
import cn.bupt.dssc.common.utils.Convert;
import cn.bupt.dssc.domain.po.FijiNews;
import cn.bupt.dssc.domain.vo.FijiNewsVO;
import cn.bupt.dssc.service.IFijiNewsService;
import cn.bupt.dssc.utils.ConvertBase64;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "新闻管理接口")
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class NewsController {
    private final IFijiNewsService newsService;

    private Convert<FijiNews, FijiNewsVO> news2vo = (FijiNews origin, FijiNewsVO target) -> {
        String uri = "/root/SPOS2.0-backend/news_img/" + origin.getId() + ".jpg"; // 服务器上的地址
        // String uri = "C:/Users/Yimin/Pictures" + "pig" + ".jpg"; // 我的机器上的地址
        target.setPic(ConvertBase64.uri2Base64(uri));
    };

    @ApiOperation("根据id查询新闻")
    @GetMapping("/{id}")
    public FijiNewsVO getNewsById(@PathVariable(required = true) int id) {
        FijiNews news = newsService.getById(id);
        return BeanUtils.copyBean(news, FijiNewsVO.class, news2vo);
    }

    @ApiOperation("根据id查询新闻")
    @GetMapping
    public FijiNewsVO getNewsById2(@RequestParam(required = true) int id) {
        FijiNews news = newsService.getById(id);
        return BeanUtils.copyBean(news, FijiNewsVO.class, news2vo);
    }

    @ApiOperation("根据关键字查询新闻")
    @GetMapping("/keyword")
    public List<FijiNewsVO> getNewsByKeyword(@RequestParam(required = true) String keyword) {
        LambdaQueryWrapper<FijiNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(FijiNews::getArticle, keyword);
        List<FijiNews> newsList = newsService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(newsList, FijiNewsVO.class, news2vo);
    }

    @ApiOperation("查询最新新闻")
    @GetMapping("/headlines")
    public List<FijiNewsVO> getHeadLines(@RequestParam(defaultValue = "10") Integer num_limit, @RequestParam(defaultValue = "") String keyword) {
        LambdaQueryWrapper<FijiNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(FijiNews::getTime)
                .like(FijiNews::getHeader, keyword)
                .isNotNull(FijiNews::getPic)
                .last("LIMIT " + num_limit);
        List<FijiNews> newsList = newsService.getBaseMapper().selectList(queryWrapper);
        return BeanUtils.copyList(newsList, FijiNewsVO.class, news2vo);
    }

}
