package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.entities.ProductEntity;
import com.springbootlearning.ecommerceapp.mapper.ProductMapper;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import com.springbootlearning.ecommerceapp.repositories.ProductRepository;
import com.springbootlearning.ecommerceapp.repositories.decorators.CategoryRepositoryDecorator;
import com.springbootlearning.ecommerceapp.repositories.decorators.ProductRepositoryDecorator;
import com.springbootlearning.ecommerceapp.service.FileService;
import com.springbootlearning.ecommerceapp.service.ProductService;
import com.springbootlearning.ecommerceapp.service.validators.ProductServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final FileService fileService;

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CategoryRepositoryDecorator categoryRepositoryDecorator;

    private final ProductRepositoryDecorator productRepositoryDecorator;

    private final ProductServiceValidator productServiceValidator;

    private final ProductMapper productMapper;

    @Value("${project.product.images}")
    private String productImageFolderPath;

    @Override
    public ProductOutDTO saveProduct(Long categoryId, ProductInDTO productInDTO) {
        CategoryEntity category = categoryRepositoryDecorator.getByIdPrimary(categoryId);
        productServiceValidator.validateNoDuplicateProductNamePresent(category, productInDTO.getProductName());

        ProductEntity productEntity = productMapper.productInDTOToProductEntity(productInDTO, category);
        ProductEntity savedProductEntity = productRepository.save(productEntity);

        return productMapper.productEntityToProductOutDTO(savedProductEntity);
    }

    @Override
    public PaginatedResponse<ProductOutDTO> getProducts(Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        List<ProductEntity> productEntities = productEntityPage.getContent();
        List<ProductOutDTO> productOutDTOs = productEntities.stream().map(productMapper::productEntityToProductOutDTO).toList();

        return productMapper.toPaginatedResponse(productEntityPage, productOutDTOs);
    }

    @Override
    public PaginatedResponse<ProductOutDTO> getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        CategoryEntity category = categoryRepositoryDecorator.getByIdPrimary(categoryId);
        Page<ProductEntity> productEntityPage = productRepository.findByCategory(category, pageable);
        List<ProductEntity> productEntities = productEntityPage.getContent();

        List<ProductOutDTO> productOutDTOs = productEntities.stream().map(productMapper::productEntityToProductOutDTO).toList();

        return productMapper.toPaginatedResponse(productEntityPage, productOutDTOs);
    }

    @Override
    public PaginatedResponse<ProductOutDTO> searchProductsByName(String productName, Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        Page<ProductEntity> productEntityPage = productRepository.findByProductNameContainsIgnoreCase(productName, pageable);

        List<ProductOutDTO> productOutDTOs = productEntityPage.getContent().stream()
                                                .map(productMapper::productEntityToProductOutDTO)
                                                .toList();

        return productMapper.toPaginatedResponse(productEntityPage, productOutDTOs);
    }

    @Override
    public ProductOutDTO findProductById(Long productId) {
        ProductEntity productEntity = productRepositoryDecorator.getByIdPrimary(productId);
        return productMapper.productEntityToProductOutDTO(productEntity);
    }

    @Override
    public ProductOutDTO updateProduct(Long categoryId, Long productId, ProductUpdateDTO productUpdateDTO) {
        CategoryEntity categoryEntity = categoryRepositoryDecorator.getByIdPrimary(categoryId);
        ProductEntity productEntity = productRepositoryDecorator.getByIdPrimary(productId);

        productServiceValidator.validateIfProductBelongsToCategory(categoryEntity, productEntity);
        if(!productEntity.getProductName().equalsIgnoreCase(productUpdateDTO.getProductName())){
            productServiceValidator.validateNoDuplicateProductNamePresent(categoryEntity, productUpdateDTO.getProductName());
        }

        productMapper.productUpdateDTOToProductEntity(productUpdateDTO, productEntity);
        productRepository.save(productEntity);

        return productMapper.productEntityToProductOutDTO(productEntity);
    }

    @Override
    public ProductOutDTO updateProductImage(Long productId, MultipartFile imageFile) {
        ProductEntity productEntity = productRepositoryDecorator.getByIdPrimary(productId);

        // upload image to server folder and get the file name of uploaded image
        String fileName = fileService.uploadImage(productImageFolderPath, imageFile);

        // Updating new file name to the product
        productEntity.setImage(fileName);
        ProductEntity savedProduct = productRepository.save(productEntity);

        return productMapper.productEntityToProductOutDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductEntity productEntity = productRepositoryDecorator.getByIdPrimary(productId);

        // Delete the image file if it exists
        if (productEntity.getImage() != null && !productEntity.getImage().isEmpty()) {
            fileService.deleteImage(productImageFolderPath, productEntity.getImage());
        }

        // Delete the product from database
        productRepository.delete(productEntity);
    }
}
