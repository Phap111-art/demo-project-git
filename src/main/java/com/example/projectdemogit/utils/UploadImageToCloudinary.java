package com.example.projectdemogit.utils;


import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.projectdemogit.exception.CustomCloudinaryException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.core.env.Environment;


public class UploadImageToCloudinary {
    public static String upload(Cloudinary cloudinary, MultipartFile file, String folder) throws CustomCloudinaryException {
        String publicId = folder + "/" + file.getOriginalFilename();
        try {
            // Lọc tất cả tài nguyên nào có tồn tại publicId trên cloudinary không
            Api api = cloudinary.api();
            Map<?, ?> resourcesResult = api.resources(ObjectUtils.asMap(
                    "type", "upload",
                    "prefix", folder
            ));
            List<Map<?, ?>> resources = (List<Map<?, ?>>) resourcesResult.get("resources");
            boolean fileExists = false;
            for (Map<?, ?> resource : resources) {
                String currentPublicId = (String) resource.get("public_id");
                if (currentPublicId.equals(publicId)) {
                    fileExists = true;
                    break;
                }
            }
            if (fileExists) {
                // File đã tồn tại
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "public_id", publicId,
                        "overwrite", true
                ));
                return publicId.substring(folder.length() + 1); // Trả về publicId của hình ảnh (đã cắt bỏ phần folder + "/")
            } else {
                // File chưa tồn tại
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "public_id", publicId,
                        "overwrite", false
                ));
                return publicId.substring(folder.length() + 1); // Trả về publicId của hình ảnh (đã cắt bỏ phần folder + "/")
            }
        } catch (IOException e) {
            throw new CustomCloudinaryException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
