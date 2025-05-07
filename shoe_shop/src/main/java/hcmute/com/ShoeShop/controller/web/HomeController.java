package hcmute.com.ShoeShop.controller.web;

import hcmute.com.ShoeShop.entity.Category;
import hcmute.com.ShoeShop.entity.Product;
import hcmute.com.ShoeShop.entity.Role;
import hcmute.com.ShoeShop.entity.Users;
import hcmute.com.ShoeShop.services.imp.CategoryService;
import hcmute.com.ShoeShop.services.imp.ProductService;
import hcmute.com.ShoeShop.services.imp.RoleService;
import hcmute.com.ShoeShop.utlis.Constant;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String getAllProducts(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "6") int size,
                                 Model model, HttpSession session) {

        Users u = (Users) session.getAttribute(Constant.SESSION_USER);

//        if(u!=null){
//            if(u.getRole().getRoleId()==3)
//            {
//                return "redirect:/user/shop";
//            }
//        }
        List<String> role = List.of("admin","manager","user","shipper");
        for(int i = 0; i < role.size(); i++){
            if(roleService.findRoleByName(role.get(i))==null){
                Role ro = new Role();
                ro.setRoleName(role.get(i));
                roleService.insertRole(ro);
            }
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        if (!categories.isEmpty()) {
            Category firstCategory = categories.get(0);
            List<Product> products = productService.getProductByCategoryId(firstCategory.getId());
            model.addAttribute("products", products);
            model.addAttribute("selectedCategory", firstCategory.getId()); // Gửi danh mục được chọn
        }
        Page<Product> productPage = productService.getPaginatedProducts(PageRequest.of(page, size));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());


        // lay ra list 20 san pham co rating cao nhat
        List<Product> ratedProducts = productService.getTopRatedProducts();
        model.addAttribute("ratedProducts", ratedProducts);
        return "/web/index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "/user/blog";
    }
}
