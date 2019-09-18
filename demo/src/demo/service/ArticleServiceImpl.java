package demo.service;

import demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @Override
    public void updateSomeArticleTitle(Long userId, String title) {
        articleRepository.updateSomeArticleTitle(userId, title);
    }
}
