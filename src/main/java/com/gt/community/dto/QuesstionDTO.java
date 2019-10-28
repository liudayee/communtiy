package com.gt.community.dto;

import com.gt.community.model.User;
import lombok.Data;

@Data
public class QuesstionDTO {
    private int id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
