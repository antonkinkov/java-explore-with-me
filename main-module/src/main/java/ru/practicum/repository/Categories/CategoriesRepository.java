package ru.practicum.repository.Categories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.category.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Category findFirstByName(String name);
}
