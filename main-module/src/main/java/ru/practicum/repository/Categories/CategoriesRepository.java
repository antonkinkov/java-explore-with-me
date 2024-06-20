package ru.practicum.repository.Categories;

import ru.practicum.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Category findFirstByName(String name);
}
