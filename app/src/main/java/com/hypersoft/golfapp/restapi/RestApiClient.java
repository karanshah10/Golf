package com.hypersoft.golfapp.restapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by Apple on 10/10/2017.
 */

public class RestApiClient {
    private static Object restApi;

    public static Object getApiService(Class apiServiceClass, String baseEndPoint, Class mockService) {

        return getMockApiService(apiServiceClass, baseEndPoint, mockService);
    }

    private static Object getMockApiService(Class apiServiceClass, String baseEndPoint, Class mockService) {

        if (restApi == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseEndPoint)
                    .build();

            // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.
            NetworkBehavior behavior = NetworkBehavior.create();
            MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                    .networkBehavior(behavior)
                    .build();

            try {
                Constructor c = mockService.getDeclaredConstructor(BehaviorDelegate.class);
                restApi = c.newInstance(mockRetrofit.create(apiServiceClass));

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return restApi;
    }
}
