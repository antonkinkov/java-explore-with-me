package ru.practicum.repository.Categories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.category.Category;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByName(String name);
}
