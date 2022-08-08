package com.ndcalabrese.topick_simple.controller;

import com.ndcalabrese.topick_simple.dto.PostDto;
import com.ndcalabrese.topick_simple.dto.VoteDto;
import com.ndcalabrese.topick_simple.model.Comment;
import com.ndcalabrese.topick_simple.model.Post;
import com.ndcalabrese.topick_simple.model.Subtopick;
import com.ndcalabrese.topick_simple.repository.SubtopickRepository;
import com.ndcalabrese.topick_simple.service.CommentService;
import com.ndcalabrese.topick_simple.service.PostService;
import com.ndcalabrese.topick_simple.service.UserService;
import com.ndcalabrese.topick_simple.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.ndcalabrese.topick_simple.model.VoteType.DOWNVOTE;
import static com.ndcalabrese.topick_simple.model.VoteType.UPVOTE;

@Controller
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    private final SubtopickRepository subtopickRepository;
    private final VoteService voteService;

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") PostDto postDto, RedirectAttributes redirectAttributes) {
        postService.save(postDto);

      redirectAttributes.addAttribute("postId", postDto.getId());

      return "redirect:/api/posts/view-post/{postId}/comments?PostCreationSuccess";
    }

    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
        Post post = new Post();
        List<Subtopick> subtopicks = subtopickRepository.findAll();

        model.addAttribute("post", post);
        model.addAttribute("subtopicks", subtopicks);

        return "create_post";
    }

    @GetMapping("/by-subtopick/{id}")
    public String getPostsBySubtopick(@PathVariable Long id, Model model) {

        List<Post> posts = postService.getAllPostsBySubtopick(id);

        model.addAttribute("posts", posts);

        return "subtopick";
    }

    // Get single post with comments
    @GetMapping("/view-post/{id}/comments")
    public String getPostWithComments(@PathVariable Long id, Model model){

        Post post = postService.getPostById(id);
        List<Comment> comments = commentService.getAllCommentsForPost(id);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "post";
    }

//    @PutMapping("/api/posts/{postId}/upvote")
//    public String upvotePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
//        VoteDto voteDto = new VoteDto();
//        voteDto.setPostId(postId);
//        voteDto.setVoteType(UPVOTE);
//        voteService.vote(voteDto);
//
//        redirectAttributes.addAttribute("postId", voteDto.getPostId());
//
//        return "redirect:/api/posts/view-post/{postId}/comments";
//
//    }

    @PutMapping("/api/posts/{postId}/downvote")
    public void downvotePost(@PathVariable Long postId) {
        VoteDto voteDto = new VoteDto();
        voteDto.setPostId(postId);
        voteDto.setVoteType(DOWNVOTE);
        voteService.vote(voteDto);

    }

}
