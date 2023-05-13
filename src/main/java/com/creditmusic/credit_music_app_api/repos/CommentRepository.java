package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
