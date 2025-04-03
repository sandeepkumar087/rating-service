//package com.chargepoint.rating.util;
//
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
//import java.io.Serializable;
//import java.util.Random;
//
//@SuppressWarnings("serial")
//public class StationRatingIdGenerator implements IdentifierGenerator {
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) {
//        Random random = new Random();
//        return "SR-" + String.format("%04d", random.nextInt(10000));
//    }
//}