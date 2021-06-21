package pl.akademia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

//@SpringBootTest
class BikeShopApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void decoderBase64() {
    System.out.println(new String(Base64.getEncoder().encode("admin:admin123".getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
    System.out.println(new String(Base64.getMimeDecoder().decode("YWRtaW46YWRtaW4xMjM="), StandardCharsets.UTF_8));
  }

}
