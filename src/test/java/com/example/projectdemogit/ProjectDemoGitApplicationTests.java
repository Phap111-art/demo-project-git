package com.example.projectdemogit;

import com.cloudinary.Cloudinary;
import com.example.projectdemogit.exception.CustomCloudinaryException;
import com.example.projectdemogit.utils.ConvertFileToMultipartFile;
import com.example.projectdemogit.utils.UploadImageToCloudinary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootTest
class ProjectDemoGitApplicationTests {

    @Value("${cloudinary.folder_product}")
    private String cloudinaryFolderProduct;

    @Autowired
    private Cloudinary cloudinary;
    @Test
    void contextLoads() throws IOException, CustomCloudinaryException {
        MultipartFile file =  ConvertFileToMultipartFile.get("upload/image1.png");
        UploadImageToCloudinary.upload(cloudinary,file,cloudinaryFolderProduct);
    }

}
