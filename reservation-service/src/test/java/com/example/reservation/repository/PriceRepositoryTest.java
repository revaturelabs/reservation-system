package com.example.reservation.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.reservation.model.BusType;
import com.example.reservation.model.PriceMatrix;
import com.example.reservation.model.SeatType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class PriceRepositoryTest {
  @Autowired
  private PriceRepository priceRepository;

  @BeforeEach
  public void beforeEach() {
    PriceMatrix pm1 = new PriceMatrix();
    pm1.setBusType(BusType.NON_AC);
    pm1.setSeatType(SeatType.CHAIR);
    pm1.setBasePrice(100);

    PriceMatrix pm2 = new PriceMatrix();
    pm2.setBusType(BusType.AC);
    pm2.setSeatType(SeatType.CHAIR);
    pm2.setBasePrice(200);

    PriceMatrix pm3 = new PriceMatrix();
    pm3.setBusType(BusType.NON_AC);
    pm3.setSeatType(SeatType.SLEEPER);
    pm3.setBasePrice(300);

    PriceMatrix pm4 = new PriceMatrix();
    pm4.setBusType(BusType.AC);
    pm4.setSeatType(SeatType.SLEEPER);
    pm4.setBasePrice(400);

    priceRepository.save(pm1);
    priceRepository.save(pm2);
    priceRepository.save(pm3);
    priceRepository.save(pm4);
  }

  @Test
  public void getNonAcChairPrice() {
    PriceMatrix priceMatrix = priceRepository.findPriceMatrix(
      BusType.NON_AC,
      SeatType.CHAIR
    );
    assertEquals(100.00, priceMatrix.getBasePrice());
  }

  @Test
  public void getAcChairPrice() {
    PriceMatrix priceMatrix = priceRepository.findPriceMatrix(
      BusType.AC,
      SeatType.CHAIR
    );
    assertEquals(200.00, priceMatrix.getBasePrice());
  }

  @Test
  public void getNoAcSleeperPrice() {
    PriceMatrix priceMatrix = priceRepository.findPriceMatrix(
      BusType.NON_AC,
      SeatType.SLEEPER
    );
    assertEquals(300.00, priceMatrix.getBasePrice());
  }

  @Test
  public void getAcSleeperPrice() {
    PriceMatrix priceMatrix = priceRepository.findPriceMatrix(
      BusType.AC,
      SeatType.SLEEPER
    );
    assertEquals(400.00, priceMatrix.getBasePrice());
  }

  @Test
  public void updatePriceMatrix() {
    PriceMatrix priceMatrix = priceRepository.findPriceMatrix(
      BusType.AC,
      SeatType.SLEEPER
    );
    priceMatrix.setBasePrice(500.00);
    priceRepository.save(priceMatrix);

    PriceMatrix updatedPriceMatrix = priceRepository.findPriceMatrix(
      BusType.AC,
      SeatType.SLEEPER
    );
    assertEquals(500.00, priceMatrix.getBasePrice());
  }

  @AfterEach
  public void afterEach() {
    priceRepository.deleteAll();
  }
}
