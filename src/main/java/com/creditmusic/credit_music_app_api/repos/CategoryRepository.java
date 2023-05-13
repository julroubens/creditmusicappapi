package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
