package app.visualmusic.cover.port.output.minio;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.shared.exception.ObjectStorageException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@RequiredArgsConstructor
public class MinioCoverImageStorageAdapter implements CoverImageOutputPort {
    private final MinioClient minioClient;

    private final Duration presignedUrlExpiration;

    @Override
    public String getUrl(Cover cover) {
        try {
            return getPresignedObjectUrl(cover.getBucket(), cover.getObject());
        } catch (Exception e) {
            throw ObjectStorageException.getPresignedUrl(e.getMessage());
        }
    }

    private String getPresignedObjectUrl(String bucket, String object) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        var args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(object)
                .expiry((int) presignedUrlExpiration.getSeconds())
                .build();

        return minioClient.getPresignedObjectUrl(args);
    }

    @Override
    public void delete(Cover cover) {
        try {
            deleteObject(cover.getBucket(), cover.getObject());
        } catch (Exception e) {
            throw ObjectStorageException.deleteObject(e.getMessage());
        }
    }

    private void deleteObject(String bucket, String object) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        var args = RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .build();

        minioClient.removeObject(args);
    }
}
