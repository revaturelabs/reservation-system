package com.example.reservation;

import com.example.reservation.email.MailService;
import com.example.reservation.model.ReservedSeats;
import com.example.reservation.repository.BusRepository;
import com.example.reservation.repository.ReservedSeatsRepository;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableMongoRepositories
public class ReservationServiceApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ReservationServiceApplication.class, args);
  }

  @Autowired
  private MailService emailService;

  @Autowired
  private ReservedSeatsRepository reservedSeatsRepository;

  @Autowired
  private BusRepository busRepository;

  @Override
  public void run(String... args) throws Exception {
    //        System.out.println("Sending Email...");
    //        emailService.sendEmail("nagcloudlab@gmail.com","hello123","this test email");
    //        System.out.println("Done");
    //
    //        ReservedSeats reservedSeats=new ReservedSeats();
    //        reservedSeats.setDate(LocalDate.now());
    //        reservedSeats.setBus(busRepository.findById("1212").get());
    //        reservedSeats.setReservedSeats(List.of(12,13));
    //
    //        reservedSeatsRepository.save(reservedSeats);

  }

  @Value("${api.common.version}")
  String apiVersion;

  @Value("${api.common.title}")
  String apiTitle;

  @Value("${api.common.description}")
  String apiDescription;

  @Value("${api.common.termsOfService}")
  String apiTermsOfService;

  @Value("${api.common.license}")
  String apiLicense;

  @Value("${api.common.licenseUrl}")
  String apiLicenseUrl;

  @Value("${api.common.externalDocDesc}")
  String apiExternalDocDesc;

  @Value("${api.common.externalDocUrl}")
  String apiExternalDocUrl;

  @Value("${api.common.contact.name}")
  String apiContactName;

  @Value("${api.common.contact.url}")
  String apiContactUrl;

  @Value("${api.common.contact.email}")
  String apiContactEmail;

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public OpenAPI getOpenApiDocumentation() {
    return new OpenAPI()
      .info(
        new Info()
          .title(apiTitle)
          .description(apiDescription)
          .version(apiVersion)
          .contact(
            new Contact()
              .name(apiContactName)
              .url(apiContactUrl)
              .email(apiContactEmail)
          )
          .termsOfService(apiTermsOfService)
          .license(new License().name(apiLicense).url(apiLicenseUrl))
      )
      .externalDocs(
        new ExternalDocumentation()
          .description(apiExternalDocDesc)
          .url(apiExternalDocUrl)
      );
  }
}
