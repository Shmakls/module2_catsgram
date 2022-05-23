package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.util.List;

@Data
public class FriendsParams {

    private String sort;
    private Integer size;
    protected List<String> friends;

}
