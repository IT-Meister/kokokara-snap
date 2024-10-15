package jp.co.itmeister.userservice.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Service {
    
    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.cloudfront.domain}")
    private String cloudfrontDomain;

    @Autowired
    public S3Service (S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile (MultipartFile file , Long postId) throws Exception {
        String contentType = file.getContentType();
        String extension = getFileExtension(contentType);
        String url = String.format("posts/%s.%s", postId , extension);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(url).contentType(contentType).build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            return url;
        } catch (S3Exception e) {
            throw new Exception("Error uploading file to S3" + e);
        }
    }

    public String getFullFileUrl (String key) {
        return "https://" + cloudfrontDomain + "/" + key;
    }

    private String getFileExtension (String contentType) {
        if(contentType.equals("image/jpeg")) {
            return "jpeg";
        } else if (contentType.equals("image/png")) {
            return "png";
        } else {
            throw new IllegalArgumentException("Unsupported file type:" + contentType);
        }
    }  

}
