package com.example.testbuoi7.controller;

import com.example.testbuoi7.entity.Department;
import com.example.testbuoi7.entity.Employee;
import com.example.testbuoi7.repository.DepartmentRepository;
import com.example.testbuoi7.repository.EmployeeRepository;
import com.example.testbuoi7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;
    // Display a list of all products
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employees/employees-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll()); //Load categories
        return "/employees/add-employee";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("employee") Employee employee,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            System.out.println(result.getAllErrors());
            return "/employees/add-employee";
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentRepository.findAll()); //Load categories
        return "/employees/update-employee";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateProduct(Employee employee,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            employee.setId(employee.getId()); // set id to keep it in the form in case of errors
            return "/employees/update-employee";
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam String keyword, Model model) {
//        model.addAttribute("products", productService.search(keyword));
//        return "/products/products-list";
//    }
//    public String saveImage(MultipartFile file) {
//        if (file.isEmpty()) {
//            return null; // Return null or a default image path if the file is empty
//        }
//        try {
//            Path rootLocation = Paths.get("upload-dir");
//            Files.createDirectories(rootLocation);
//            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//            Path targetLocation = rootLocation.resolve(filename);
//            Files.copy(file.getInputStream(), targetLocation);
//            return targetLocation.toString();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), e);
//        }
//    }
}
