package pl.patrykkawula.servicesupply.productdetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.patrykkawula.servicesupply.brand.BrandService;
import pl.patrykkawula.servicesupply.picture.Picture;
import pl.patrykkawula.servicesupply.productdetails.dtos.ProductDetailsDto;
import pl.patrykkawula.servicesupply.productdetails.dtos.ProductDetailsSaveDto;

import java.util.List;

@Controller
@RequestMapping(("/productDetails"))
class ProductDetailsController {
    private final ProductDetailsService productDetailsService;
    private final BrandService brandService;

    ProductDetailsController(ProductDetailsService productDetailsService, BrandService brandService) {
        this.productDetailsService = productDetailsService;
        this.brandService = brandService;
    }

    @GetMapping("/")
    String getAllProductDetails(Model model) {
        List<ProductDetailsDto> productDetailsList = productDetailsService.allProductDetails();
        model.addAttribute("productDetailsList", productDetailsList);
        return "product_details";
    }

    @GetMapping("/showNewProductForm")
    String showNewEmployeeForm(Model model) {
        List<String> allBrandName = brandService.getAllBrandName();
        ProductDetailsSaveDto productDetailsSaveDto = ProductDetailsSaveDto.builder().build();
        model.addAttribute("productDetailsSaveDto", productDetailsSaveDto);
        model.addAttribute("brandName", allBrandName);
        return "new_product_details";
    }

    @PostMapping("/saveProductDetails")
    public String saveProduct(@ModelAttribute("productDetailsSaveDto")ProductDetailsSaveDto productDetailsSaveDto) {
        productDetailsService.saveProductDetails(productDetailsSaveDto);
        return "redirect:/productDetails/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") Long id, Model model) {
        List<String> allBrandName = brandService.getAllBrandName();
        ProductDetailsSaveDto productDetailsSaveDto = productDetailsService.findProductDetailsById(id);
        model.addAttribute("productDetailsSaveDto", productDetailsSaveDto);
        model.addAttribute("brandName", allBrandName);
        return "update_product_details";
    }

    @GetMapping("/deleteProductDetails/{id}")
    public String deleteProduct(@PathVariable (value = "id") Long id) {
        productDetailsService.deleteProductDetailsById(id);
        return "redirect:/productDetails/";
    }
    
    @GetMapping("/getProductInfo/{id}")
    public String getProductInfo(@PathVariable (value = "id") Long id, Model model) {
        ProductDetailsSaveDto productDetailsSaveDto = productDetailsService.findProductDetailsById(id);
        Picture productPicture = productDetailsService.productImage(productDetailsSaveDto.brand());
        model.addAttribute("productDetailsSaveDto", productDetailsSaveDto);
        model.addAttribute("productPicture", productPicture);
        return "product_details_info";
    }
}
