package az.qonaqol.qonaqol.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {
    public static final String ACCESS_KEY = "AKIA6ODVAQ25W7NCUNM2";
    public static final String SECRET_KEY = "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";

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        var awsS3Config = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.EU_NORTH_1) // This field if not exist throws an exception
                .build();
        return awsS3Config;
    }

}
