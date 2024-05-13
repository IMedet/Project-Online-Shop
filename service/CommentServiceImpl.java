package kz.medet.onlineshop.service;

import kz.medet.onlineshop.model.Comment;
import kz.medet.onlineshop.repository.CommentRepository;
import kz.medet.onlineshop.repository.ProductRepository;
import kz.medet.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void addComment(Long userId, Long productId, String comment){
        Comment comment1 = new Comment();
        comment1.setComment(comment);

        comment1.setProduct(productRepository.findById(productId).orElseThrow());

        comment1.setUser(userRepository.findById(userId).orElseThrow());

        commentRepository.save(comment1);
    }

    public List<Comment> getAllByProductId(Long id){
        return commentRepository.findAllByProduct_Id(id);
    }
}
