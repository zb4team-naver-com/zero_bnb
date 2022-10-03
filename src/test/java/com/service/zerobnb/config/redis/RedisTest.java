package com.service.zerobnb.config.redis;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

@SpringBootTest
@Disabled
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void rightInsertAndGetTest() {
        ListOperations<String, WishLike> likePostListOperations = redisTemplate.opsForList();
        String key = String.format("wish-list", "apdh1709@gmail.com");

        likePostListOperations.rightPush(key, new WishLike(1L, "숙소1", "https://picture.org/숙소1.jpg"));
        likePostListOperations.rightPush(key, new WishLike(2L, "숙소2", "https://picture.org/숙소2.jpg"));
        likePostListOperations.rightPush(key, new WishLike(3L, "숙소3", "https://picture.org/숙소3.jpg"));

        likePostListOperations.range(key, 0, likePostListOperations.size(key)).forEach(likePost -> System.out.println(likePost.toString()));
        likePostListOperations.leftPop(key);

        System.out.println("제거 후\n");

        likePostListOperations.range(key, 0, likePostListOperations.size(key)).forEach(likePost -> System.out.println(likePost.toString()));
    }

    public static class WishLike implements Serializable {
        private static final long serialVersionUID = -6584044926029805156L;
        private Long id;
        private String title;
        private String thumbnailImage;

        public WishLike(Long id, String title, String thumbnailImage) {
            this.id = id;
            this.title = title;
            this.thumbnailImage = thumbnailImage;
        }

        @Override
        public String toString() {
            return "WishLike{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", thumbnailImage='" + thumbnailImage + '\'' +
                    '}';
        }
    }
}
