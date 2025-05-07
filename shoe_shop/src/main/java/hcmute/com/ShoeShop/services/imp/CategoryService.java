package hcmute.com.ShoeShop.services.imp;

import hcmute.com.ShoeShop.entity.Category;
import hcmute.com.ShoeShop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {return categoryRepository.findAll();};

    public int count(){
        return (int)categoryRepository.count();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
