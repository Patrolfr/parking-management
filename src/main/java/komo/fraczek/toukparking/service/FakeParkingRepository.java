package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.domain.ParkingMeter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//public class FakeParkingRepository implements ParkingRepository{
public class FakeParkingRepository{


    public static List<ParkingMeter> list = new LinkedList<>();

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    /////////////
//
//    @Override
//    public <S extends ParkingMeter> S save(S s) {
//        return list.add(s) == true ? s : s;
//    }
//
//    @Override
//    public <S extends ParkingMeter> Iterable<S> saveAll(Iterable<S> iterable) {
//        iterable.forEach( item -> list.add(item));
//        Iterable<ParkingMeter> iterable1 = list;
//        return iterable;
//    }
//
//    @Override
//    public Optional<ParkingMeter> findById(Long aLong) {
//
//        ParkingMeter meterById = list.stream().filter(parkingMeter -> parkingMeter.getId().equals(aLong)).limit(1L).collect(toSingleton());
//
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public Iterable<ParkingMeter> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<ParkingMeter> findAllById(Iterable<Long> iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(ParkingMeter parkingMeter) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends ParkingMeter> iterable) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
}
