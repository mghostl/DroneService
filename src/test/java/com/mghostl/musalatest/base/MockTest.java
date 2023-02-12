package com.mghostl.musalatest.base;

import com.mghostl.musalatest.DroneApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DroneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public interface MockTest{
}
