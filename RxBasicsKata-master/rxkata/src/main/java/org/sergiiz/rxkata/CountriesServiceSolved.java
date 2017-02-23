package org.sergiiz.rxkata;

import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.Single;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {
        //return null; // put your solution here
        Single<String> singleCountry = Single.just(country.getName().toUpperCase());
        return singleCountry;
    }

    public Single<Integer> countCountries(List<Country> countries) {
        //return null; // put your solution here
        Single<Integer> countriesNumer = Single.just(countries.size());
        return countriesNumer;
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        Observable<long> populationObservable = Observable.fromIterable(countries.forEach(country -> country.getPopulation()););
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(final FutureTask<List<Country>> countriesFromNetwork) {
        return null; // put your solution here
    }

    @Override
    public Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries) {
        return null; // put your solution here
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                                     Observable<Country> countryObservable2) {
        return null; // put your solution here
    }

    @Override
    public Single<Boolean> areEmittingSameSequences(Observable<Country> countryObservable1,
                                                    Observable<Country> countryObservable2) {
        return null; // put your solution here
    }
}
