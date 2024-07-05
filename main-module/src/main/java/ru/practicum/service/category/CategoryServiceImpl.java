package ru.practicum.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.exception.ConflictException;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.model.category.Category;
import ru.practicum.repository.Categories.CategoriesRepository;
import ru.practicum.repository.Event.EventRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoriesRepository categoriesRepository;
    private final EventRepository eventsRepository;

    @Override
    public List<CategoryDto> getCategories(int from, int size) {
        List<Category> categories = categoriesRepository.findAll(PageRequest.of(from, size)).getContent();

        return categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = findById(id);
        return CategoryMapper.toDto(category);
    }

    private Category findById(Long id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: ", id)));
    }

    @Override
    @Transactional
    public CategoryDto create(NewCategoryDto newCategoryDto) {
        Category existingCategory = categoriesRepository.findFirstByName(newCategoryDto.getName());

        if (existingCategory != null) {
            throw new ConflictException("Категория с таким именем уже существует");
        }

        Category category = categoriesRepository.save(new Category(newCategoryDto.getName()));
        return new CategoryDto(category.getId(),category.getName());
    }

    @Override
    @Transactional
    public void delete(Long catId) {
            categoriesRepository.delete(findById(catId));
    }

    @Override
    @Transactional
    public CategoryDto update(Long catId, CategoryDto categoryDto) {
        Category category = findById(catId);
        Category existingCategory = categoriesRepository.findFirstByName(categoryDto.getName());
        boolean isCurrent = existingCategory != null && existingCategory.getName().equals(categoryDto.getName()) && Objects.equals(catId, existingCategory.getId());
        if (!isCurrent  &&  existingCategory != null) {
            throw new ConflictException("Категория с таким именем уже существует");
        }
        category.setName(categoryDto.getName());
        return CategoryMapper.toDto(category);
    }
}