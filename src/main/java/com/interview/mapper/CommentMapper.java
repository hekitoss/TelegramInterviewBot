package com.interview.mapper;

import com.interview.dto.CommentDto;
import com.interview.entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private final ModelMapper modelMapper;

    public CommentMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Comment.class, CommentDto.class)
                .addMapping(comment -> comment.getCreator().getUsername(), CommentDto::setCreatorUserName)
                .addMapping(comment -> comment.getCreator().getId(), CommentDto::setUserId);
    }

    public CommentDto commentDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
}
