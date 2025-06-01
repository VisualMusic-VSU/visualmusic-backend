package app.visualmusic.cover.shared.exception;

public class ObjectStorageException extends RuntimeException {
    private ObjectStorageException(String message) {
        super(message);
    }

    public static ObjectStorageException getPresignedUrl(String message) {
        return new ObjectStorageException("Error when receiving presigned URL: " + message);
    }

    public static ObjectStorageException deleteObject(String message) {
        return new ObjectStorageException("Error deleting an object: " + message);
    }
}
