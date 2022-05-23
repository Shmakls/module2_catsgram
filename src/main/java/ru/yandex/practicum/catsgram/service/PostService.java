package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.IdNotExistException;
import ru.yandex.practicum.catsgram.exceptions.PostNotFoundException;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    private static Integer globalId = 0;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(Integer size, String sort, Integer from) {

            return posts.stream().sorted((p0, p1) -> {
                int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
                if(sort.equals("desc")){
                    comp = -1 * comp; //обратный порядок сортировки
                }
                return comp;
            }).skip(from).limit(size).collect(Collectors.toList());
    }

    private static Integer getNextId(){
        return globalId++;
    }

    public Post create(Post post) {

        if (userService.checkUserBeforePost(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }

        post.setId(getNextId());
        posts.add(post);
        return post;
    }

    public Post findPostById(Integer postId) {

        if (postId == null) {
            throw new IdNotExistException("ID отсутствует.");
        }

        Optional<Post> post = posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();

        if (post.isEmpty()) {
            throw new PostNotFoundException("Пост с ID " + postId + " не найден.");
        }

        return post.get();

    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.stream().filter(p -> email.equals(p.getAuthor())).sorted((p0, p1) -> {
            int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        }).limit(size).collect(Collectors.toList());
    }

}
