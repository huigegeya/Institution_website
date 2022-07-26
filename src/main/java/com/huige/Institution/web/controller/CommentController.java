package com.huige.Institution.web.controller;

import com.huige.Institution.domain.entity.Comment;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.service.ICommentService;
import com.huige.Institution.util.ContentUtil;
import com.huige.Institution.util.SecurityUtil;
import com.huige.Institution.util.ip.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/blog/comment")
@Validated
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/listPage")
    public AjaxResult listPage() {
        return AjaxResult.pageResult(commentService.listPage());
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody Comment comment, HttpServletRequest request) {
        // 去除js脚本
        String content = ContentUtil.cleanXSS(comment.getContent());
        content = ContentUtil.emoji2code(content);
        comment.setContent(content);
        comment.setCreated(new Date());
        comment.setAuthor(SecurityUtil.getUsername());
        comment.setIp(IpUtil.getIpAddr(request));
        return AjaxResult.updateResult(commentService.add(comment), "发布评论");
    }

    @DeleteMapping("/{id}")
    public AjaxResult delete(@NotNull @PathVariable Integer id) {
        return AjaxResult.updateResult(commentService.delete(id), "删除");
    }
}
