package com.claude.config;

import com.claude.entity.Article;
import com.claude.entity.User;
import com.claude.repository.ArticleRepository;
import com.claude.repository.UserRepository;
import com.claude.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final AuthService authService;

    public DataInitializer(UserRepository userRepository, ArticleRepository articleRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.authService = authService;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.findByUsername("admin").isPresent()) {
            authService.register("admin", "admin123");
            System.out.println("默认用户创建成功: admin / admin123");
        }

        if (articleRepository.count() == 0) {
            Article article1 = new Article();
            article1.setTitle("Claude Code 入门指南");
            article1.setExcerpt("本文将介绍 Claude Code 的基本概念和使用方法，帮助你快速上手这个强大的工具。");
            article1.setContent("# Claude Code 入门指南\n\n## 什么是 Claude Code？\n\nClaude Code 是一个由 Anthropic 开发的 AI 辅助编程工具，它可以帮助你更高效地编写代码、调试问题和学习新技术。\n\n## 主要功能\n\n1. **智能代码补全** - 根据上下文自动补全代码\n2. **代码解释** - 解释复杂代码的工作原理\n3. **bug 修复** - 帮助定位和修复代码问题\n4. **重构建议** - 提供代码重构的建议\n\n## 开始使用\n\n首先，你需要安装 Claude Code CLI 工具，然后通过简单的命令即可开始使用。\n\n```bash\nnpm install -g claude-code\n```\n\n希望这篇文章对你有所帮助！");
            article1.setCategory("入门教程");
            article1.setTags("[\"Claude Code\", \"入门\", \"教程\"]");
            article1.setStatus(Article.ArticleStatus.PUBLISHED);
            article1.setReadTime("5分钟");
            article1.setAuthor("Claude");
            article1.setSortOrder(1);

            Article article2 = new Article();
            article2.setTitle("使用 Superpowers 技能");
            article2.setExcerpt("了解如何使用 Claude Code 的 Superpowers 技能来提升你的开发效率。");
            article2.setContent("# 使用 Superpowers 技能\n\nSuperpowers 是 Claude Code 的一组强大技能，它们可以帮助你完成各种开发任务。\n\n## 核心技能\n\n- **brainstorming** - 头脑风暴和设计\n- **writing-plans** - 编写实现计划\n- **subagent-driven-development** - 子代理驱动开发\n- **test-driven-development** - 测试驱动开发\n\n每个技能都有其特定的用途，你可以根据项目需要选择合适的技能。\n\n## 如何使用\n\n在 Claude Code 中，你可以通过 `/skill` 命令来调用各种技能，或者让系统自动选择合适的技能。\n\n祝你使用愉快！");
            article2.setCategory("高级技巧");
            article2.setTags("[\"Superpowers\", \"技能\", \"效率\"]");
            article2.setStatus(Article.ArticleStatus.PUBLISHED);
            article2.setReadTime("8分钟");
            article2.setAuthor("Claude");
            article2.setSortOrder(2);

            Article article3 = new Article();
            article3.setTitle("草稿文章示例");
            article3.setExcerpt("这是一个草稿状态的文章示例。");
            article3.setContent("# 草稿文章\n\n这篇文章还在编写中...");
            article3.setCategory("未分类");
            article3.setTags("[]");
            article3.setStatus(Article.ArticleStatus.DRAFT);
            article3.setReadTime("待定");
            article3.setAuthor("Admin");
            article3.setSortOrder(0);

            articleRepository.saveAll(Arrays.asList(article1, article2, article3));
            System.out.println("示例文章创建成功！");
        }
    }
}
