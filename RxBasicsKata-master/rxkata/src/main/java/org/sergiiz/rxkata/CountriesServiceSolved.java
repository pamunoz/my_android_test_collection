package org.sergiiz.rxkata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {
        //return null; // put your solution here
        return Single.just(country.getName().toUpperCase());
    }

    public Single<Integer> countCountries(List<Country> countries) {
        //return null; // put your solution here
        return Single.just(countries.size());
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable
                .fromIterable(countries)
                .flatMap(country -> Observable.just(country.getPopulation()));
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {
        return Observable
                .fromIterable(countries)
                .flatMap(country -> Observable.just(country.getName()));
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        return Observable
                .fromIterable(countries)
                .skip(2)
                .take(2);
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {


        return Single.create(emiter -> {
            try {
                if (countries != null && !countries.isEmpty()) {
                    boolean value = true;
                    outerloop:
                    for (Country country : countries) {
                        if (country.getPopulation() > 1_000_000L) {
                            value = true;
                        } else {
                            value = false;
                            break outerloop;
                        }
                    }
                    emiter.onSuccess(value);
                }

            } catch (Exception e) {
                emiter.onError(e);
            }
        });
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {
        TimedEvent
        return Observable.fromIterable(countries)
                .filter(country -> country.getPopulation() > 1_000_000L);
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(final FutureTask<List<Country>> countriesFromNetwork) {
        countriesFromNetwork.run();

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
