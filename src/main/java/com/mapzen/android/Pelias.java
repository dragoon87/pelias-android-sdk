package com.mapzen.android;

import com.mapzen.android.gson.Result;

import retrofit.Callback;
import retrofit.RestAdapter;

public class Pelias {
    private PeliasService service;
    public static final String DEFAULT_SERVICE_ENDPOINT = "http://pelias.test.mapzen.com/";
    static Pelias instance = null;

    protected Pelias(PeliasService service) {
        this.service = service;
    }

    private Pelias(String endpoint) {
        initService(endpoint);
    }

    private Pelias() {
        initService(DEFAULT_SERVICE_ENDPOINT);
    }

    private void initService(String serviceEndpoint) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(serviceEndpoint)
                .build();
        this.service = restAdapter.create(PeliasService.class);
    }

    public static Pelias getPeliasWithEndpoint(String endpoint) {
        if (instance == null) {
            instance = new Pelias(endpoint);
        }
        return instance;
    }

    public static Pelias getPelias() {
        if (instance == null) {
            instance = new Pelias();
        }
        return instance;
    }

    public void suggest(String query, Callback<Result> callback) {
        service.getSuggest(query, callback);
    }

    public void search(String query, String viewbox, Callback<Result> callback) {
        service.getSearch(query, viewbox, callback);
    }
}
