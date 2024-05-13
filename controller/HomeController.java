package kz.medet.onlineshop.controller;

import kz.medet.onlineshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final OrderServiceImpl orderService;
    private final CategoryServiceImpl categoryService;
    private final CommentServiceImpl commentService;
    private final ProductQuantityServiceImpl productQuantityService;

    @GetMapping(value = "/getCategoriesPage")
    public String homePage(Model model){
        model.addAttribute("categoriesList", categoryService.getAllCategories());
        model.addAttribute("highestProduct", productService.getHighestProducts());
        return "main";
    }

    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("categoriesList", categoryService.getAllCategories());
        return "general";
    }


    @GetMapping(value = "/categoryLayout")
    public String categoryLayout(Model model){
        model.addAttribute("categoriesList" , categoryService.getAllCategories());

        return "/vendor/categoriesLayout";
    }

    @GetMapping(value = "/categoryDetails/{id}")
    public String getCategoryDetails(@PathVariable(value = "id") Long id,
                                     Model model){
        model.addAttribute("categoriesList", categoryService.getAllCategories());
        model.addAttribute("products", productService.findAllByCategory_Id(id));
        return "categoryDetails";
    }

    @GetMapping(value = "/productDetails/{id}")
    public String getProductDetails(@PathVariable(value = "id") Long id,
                                     Model model){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("comments" , commentService.getAllByProductId(id));
        model.addAttribute("productQuantities", productQuantityService.getProductQuantitiesByProductId(id));
        return "productDetails";
    }

    @GetMapping(value = "/sign-up")
    @PreAuthorize("isAnonymous()")
    public String getSignUpPage(){
        return "sign-up";
    }

    @GetMapping(value = "/sign-in")
    @PreAuthorize("isAnonymous()")
    public String getSignInPage(){
        return "sign-in";
    }

    @GetMapping(value = "/entering")
    public String entering(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password){
        Boolean result = userService.entering(email,password);

        if(result != null){
            if(result){
                return "redirect:/profile";
            }

            return "redirect:/passwordError";
        }

        return "redirect:/emailError";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("orders", orderService.getAllOrdersByBuyerId(userService.getCurrentUser().getId()));
        return "profile";
    }

    @PostMapping(value = "/registration")
    @PreAuthorize("isAnonymous()")
    public String registration(@RequestParam(name = "user_email") String email,
                               @RequestParam(name = "user_password") String password,
                               @RequestParam(name = "user_repeat_password") String repeatPassword,
                               @RequestParam(name = "user_full_name") String fullName){
        Boolean result = userService.signUp(email, password, repeatPassword, fullName);

        if(result != null){
            if(result){
                return "redirect:/sign-up?success";
            }
            return "redirect:/sign-up?passwordError";
        }
        return "redirect:/sign-up?emailError";
    }

    @PostMapping(value = "/sign-out")
    @PreAuthorize("isAuthenticated()")
    public String getSignOutPage(){
        return "redirect:/";
    }

    @GetMapping(value = "/change-password")
    @PreAuthorize("isAuthenticated()")
    public String changePassword(){
        return "change-password";
    }

    @PostMapping(value = "/save-password")
    @PreAuthorize("isAuthenticated()")
    public String savePassword(@RequestParam(name = "user_old_password") String oldPassword,
                               @RequestParam(name = "user_new_password") String newPassword,
                               @RequestParam(name = "user_repeat_new_password") String repeatNewPassword){
        Boolean result = userService.updatePassword(oldPassword, newPassword, repeatNewPassword);

        if(result != null) {
            if (result){
                return "redirect:/change-password?success";
            }

            return "redirect:/change-password?newPasswordError";
        }

        return "redirect:/change-password?oldPasswordError";

    }

    @PostMapping(value = "/addComment")
    @PreAuthorize("isAuthenticated()")
    public String addComment(@RequestParam(name = "comment") String comment,
                             @RequestParam(name = "productId") Long productId){
        commentService.addComment(userService.getCurrentUser().getId(),productId,comment);

        return "redirect:/productDetails/" + productId;
    }

    @PostMapping(value = "/toCart")
    @PreAuthorize("isAuthenticated()")
    public String toCart(@RequestParam(name = "selectedSellerId") Long sellerId,
                         @RequestParam(name = "amount") int amountChoosed,
                         @RequestParam(name = "productId") Long productId){
        orderService.saveOrder(sellerId,amountChoosed,productId);
        productQuantityService.decreaseAmountOfProductBySellerId(sellerId,productId,amountChoosed);
        return "redirect:/productDetails/"+productId;
    }

    @GetMapping(value = "/myProducts")
    @PreAuthorize("isAuthenticated()")
    public String myProducts(Model model){
        model.addAttribute("products", productQuantityService.getMyProducts());
        model.addAttribute("categories" , categoryService.getAllCategories());
        return "myProducts";
    }

    @PostMapping(value = "/addProduct")
    @PreAuthorize("isAuthenticated()")
    public String addProduct(@RequestParam(name = "productName") String productName,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "price") Double price,
                             @RequestParam(name = "imageUrl") String imageUrl,
                             @RequestParam(name = "categoryId") Long categoryId,
                             @RequestParam(name = "amount") int amount){
        productService.addProduct(productName, description, price, imageUrl, categoryId);
        productQuantityService.addProduct(amount,productService.getProductByImageUrl(imageUrl));

        return "redirect:/myProducts";
    }

    @GetMapping(value = "/change-name")
    @PreAuthorize("isAuthenticated()")
    public String changeName(){
        return "changeName";
    }

    @PostMapping(value = "/saveName")
    @PreAuthorize("isAuthenticated()")
    public String saveName(@RequestParam(name = "newName") String newName){
        userService.saveNewUserName(newName);
        return "redirect:/profile";
    }

    @PostMapping(value = "/deleteOrder/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteOrder(@PathVariable(value = "id") Long id){
        productQuantityService.afterDeletedOrder(id);
        orderService.deleteOrder(id);
        return "redirect:/profile";
    }
}
